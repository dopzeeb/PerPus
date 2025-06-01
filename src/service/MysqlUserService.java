package service;

import java.sql.Connection;
import java.util.ArrayList;

import model.user;

public class MysqlUserService {
    private Connection koneksi;

    public MysqlUserService() {
        this.koneksi = util.MysqlUtilities.getConnection();
    }


    public user createVisitor() {
        return new model.visitor();
    }

    public user createLibrarian() {
        return new model.librarian();
    }

    public ArrayList<user> getAllUsers() {
        ArrayList<user> users = new ArrayList<>();
        // Implement logic to retrieve all users from the database
        String sql = "SELECT * FROM user"; // Adjust the SQL query as needed
        try {
            var statement = koneksi.createStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Boolean isLibrarian = resultSet.getBoolean("tipe");
                user u;
                if (isLibrarian) {
                    u = new model.librarian(
                        resultSet.getInt("id_user"),
                        resultSet.getString("nama"),
                        resultSet.getString("alamat"),
                        resultSet.getString("noTelp"),
                        resultSet.getString("IDPetugas")
                    );
                } else {
                    u = new model.visitor(
                        resultSet.getInt("id_user"),
                        resultSet.getString("nama"),
                        resultSet.getString("alamat"),
                        resultSet.getString("noTelp"),
                        resultSet.getString("MemberID")
                    );
                }
                users.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public void addUser(user u) {
        // Implement logic to add a user to the database
        String sql = "INSERT INTO user (nama, alamat, noTelp, tipe, IDPetugas, MemberID) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            var preparedStatement = koneksi.prepareStatement(sql);
            preparedStatement.setString(1, u.getNama());
            preparedStatement.setString(2, u.getAlamat());
            preparedStatement.setString(3, u.getNoTelp());
            if (u instanceof model.librarian) {
                preparedStatement.setBoolean(4, true);
                preparedStatement.setString(5, ((model.librarian) u).getIDPetugas());
                preparedStatement.setNull(6, java.sql.Types.VARCHAR); // No MemberID for librarian
            } else {
                preparedStatement.setBoolean(4, false);
                preparedStatement.setNull(5, java.sql.Types.VARCHAR); // No IDPetugas for visitor
                preparedStatement.setString(6, ((model.visitor) u).getMemberID());
            }
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(user u) {
        // Implement logic to update a user in the database
        String sql = "UPDATE user SET nama = ?, alamat = ?, noTelp = ?, tipe = ?, IDPetugas = ?, MemberID = ? WHERE id_user = ?";
        try {
            var preparedStatement = koneksi.prepareStatement(sql);
            preparedStatement.setString(1, u.getNama());
            preparedStatement.setString(2, u.getAlamat());
            preparedStatement.setString(3, u.getNoTelp());
            if (u instanceof model.librarian) {
                preparedStatement.setBoolean(4, true);
                preparedStatement.setString(5, ((model.librarian) u).getIDPetugas());
                preparedStatement.setNull(6, java.sql.Types.VARCHAR); // No MemberID for librarian
            } else {
                preparedStatement.setBoolean(4, false);
                preparedStatement.setNull(5, java.sql.Types.VARCHAR); // No IDPetugas for visitor
                preparedStatement.setString(6, ((model.visitor) u).getMemberID());
            }
            preparedStatement.setInt(7, u.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(user u) {
        // Implement logic to delete a user from the database
        String sql = "DELETE FROM user WHERE id_user = ?";
        try {
            var preparedStatement = koneksi.prepareStatement(sql);
            preparedStatement.setInt(1, u.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public user getUserById(int id) {
        user u = null;
        String sql = "SELECT * FROM user WHERE id_user = ?";
        try {
            var preparedStatement = koneksi.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Boolean isLibrarian = resultSet.getBoolean("tipe");
                if (isLibrarian) {
                    u = new model.librarian(
                        resultSet.getInt("id_user"),
                        resultSet.getString("nama"),
                        resultSet.getString("alamat"),
                        resultSet.getString("noTelp"),
                        resultSet.getString("IDPetugas")
                    );
                } else {
                    u = new model.visitor(
                        resultSet.getInt("id_user"),
                        resultSet.getString("nama"),
                        resultSet.getString("alamat"),
                        resultSet.getString("noTelp"),
                        resultSet.getString("MemberID")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;       
    }
}
