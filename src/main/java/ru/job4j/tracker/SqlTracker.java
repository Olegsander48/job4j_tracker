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
        List<Item> itemList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select * from items")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    itemList.add(createItemFromResultSet(resultSet));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> itemList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM items WHERE name = (?)")) {
            preparedStatement.setString(1, key);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    itemList.add(createItemFromResultSet(resultSet));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return itemList;
    }

    @Override
    public Item findById(int id) {
        Item result = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM items WHERE id = (?)")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result = createItemFromResultSet(resultSet);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return result;
    }

    private Item createItemFromResultSet(ResultSet resultSet) {
        Item item = null;
        try {
            item = new Item(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getTimestamp("created").toLocalDateTime().withNano(0)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
}
