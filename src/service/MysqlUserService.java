package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.user;
import model.librarian;
import model.visitor;

public class MysqlUserService {
    private Connection koneksi;

    public MysqlUserService() {
        this.koneksi = util.MysqlUtilities.getConnection();
    }

    public user createVisitor() {
        return new visitor();
    }

    public user createLibrarian() {
        return new librarian();
    }

    public ArrayList<user> getAllUsers() {
        ArrayList<user> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try {
            var statement = koneksi.createStatement();
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String tipe = resultSet.getString("tipe");
                user u;
                if ("librarian".equals(tipe)) {
                    u = new librarian(
                        resultSet.getInt("id_user"),
                        resultSet.getString("nama"),
                        resultSet.getString("alamat"),
                        resultSet.getString("noTelp"),
                        resultSet.getString("IDPetugas")
                    );
                } else {
                    u = new visitor(
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
        String sql = "INSERT INTO user (nama, alamat, noTelp, tipe, IDPetugas, MemberID) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            var preparedStatement = koneksi.prepareStatement(sql);
            preparedStatement.setString(1, u.getNama());
            preparedStatement.setString(2, u.getAlamat());
            preparedStatement.setString(3, u.getNoTelp());

            if (u instanceof librarian) {
                preparedStatement.setString(4, "librarian");
                preparedStatement.setString(5, ((librarian) u).getIDPetugas());
                preparedStatement.setNull(6, java.sql.Types.VARCHAR);
            } else {
                preparedStatement.setString(4, "visitor");
                preparedStatement.setNull(5, java.sql.Types.VARCHAR);
                preparedStatement.setNull(6, java.sql.Types.VARCHAR);
            }
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(user u) {
        String sql = "UPDATE user SET nama = ?, alamat = ?, noTelp = ?, tipe = ?, IDPetugas = ?, MemberID = ? WHERE id_user = ?";
        try {
            var preparedStatement = koneksi.prepareStatement(sql);
            preparedStatement.setString(1, u.getNama());
            preparedStatement.setString(2, u.getAlamat());
            preparedStatement.setString(3, u.getNoTelp());

            if (u instanceof librarian) {
                preparedStatement.setString(4, "librarian");
                preparedStatement.setString(5, ((librarian) u).getIDPetugas());
                preparedStatement.setNull(6, java.sql.Types.VARCHAR);
            } else {
                preparedStatement.setString(4, "visitor");
                preparedStatement.setNull(5, java.sql.Types.VARCHAR);
                preparedStatement.setString(6, ((visitor) u).getMemberID());
            }

            preparedStatement.setInt(7, u.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(user u) {
        String sql = "DELETE FROM user WHERE id_user = ?";
        try {
            var preparedStatement = koneksi.prepareStatement(sql);
            preparedStatement.setInt(1, u.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public user getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM user WHERE id_user = ?";
        PreparedStatement stmt = koneksi.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String tipe = rs.getString("tipe");

            if ("librarian".equals(tipe)) {
                return new librarian(
                    rs.getInt("id_user"),
                    rs.getString("nama"),
                    rs.getString("alamat"),
                    rs.getString("noTelp"),
                    rs.getString("IDPetugas")
                );
            } else {
                return new visitor(
                    rs.getInt("id_user"),
                    rs.getString("nama"),
                    rs.getString("alamat"),
                    rs.getString("noTelp"),
                    rs.getString("MemberID")
                );
            }
        }

        return null;
    }
}
