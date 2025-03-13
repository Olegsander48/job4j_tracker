package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {

    private Connection connection;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection connection) {
        this.connection = connection;
    }

    private void init() {
        try (InputStream input = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(input);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO items(name, created) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            preparedStatement.execute();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        Item findItem = findById(id);
        boolean result = findItem != null;
        if (result) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE items SET name = (?), created = (?) WHERE id = (?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, item.getName());
                preparedStatement.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
                preparedStatement.setInt(3, id);
                preparedStatement.execute();
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        item.setId(generatedKeys.getInt(1));
                    }
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM items WHERE id = (?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM items")) {
            items = writeToList(preparedStatement);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM items WHERE name = (?)")) {
            preparedStatement.setString(1, key);
            items = writeToList(preparedStatement);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item result = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM items WHERE id = (?)")) {
            preparedStatement.setInt(1, id);
            List<Item> items = writeToList(preparedStatement);
            if (!items.isEmpty()) {
                result = items.get(0);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    /**
     * This method get values from statement to ResultSet and insert them to List<Item>
     *
     * @param preparedStatement from we get ResultSet
     * @return list of Items
     */
    private List<Item> writeToList(PreparedStatement preparedStatement) {
        List<Item> items = new ArrayList<>();
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                items.add(new Item(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getTimestamp("created").toLocalDateTime()
                ));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return items;
    }
}
