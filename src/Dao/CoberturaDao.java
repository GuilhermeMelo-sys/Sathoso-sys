/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pizzaria.Interfaces.Dao;
import pizzaria.Model.Cobertura;

/**
 *
 * @author Pichau
 */
public class CoberturaDao implements Dao<Cobertura> {

    private Connection conn;
    private ResultSet rs;
    private PreparedStatement ps;
    private String query;

    @Override
    public ArrayList<Cobertura> pegar(int id) {
        try {
            conn = ConnectionFactory.connection();
            ps = conn.prepareStatement("SELECT * FROM cobertura WHERE id = ?;");
            ps.clearParameters();
            ps.setInt(1, id);

            rs = ps.executeQuery();

            return gerarLista();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    @Override
    public Cobertura pegarUnico(int id) {
        try {
            conn = ConnectionFactory.connection();
            ps = conn.prepareStatement("SELECT * FROM cobertura WHERE id = ?;");
            ps.clearParameters();
            ps.setInt(1, id);

            rs = ps.executeQuery();
            rs.next();
            return gerarObjeto();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public ArrayList<Cobertura> pegarTudo() {
        try {
            conn = ConnectionFactory.connection();
            ps = conn.prepareStatement("SELECT * FROM cobertura;");
            rs = ps.executeQuery();

            return gerarLista();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public void adicionar(Cobertura cobertura) {
        try {
            conn = ConnectionFactory.connection();
            ps = conn.prepareStatement("INSERT INTO cobertura (id, nome, valor) "
                    + "values(?, ?, ?);");

            ps.clearParameters();
            criarParamatrosObjeto(cobertura);

            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public void deletarDado(Cobertura cobertura) {
        try {
            conn = ConnectionFactory.connection();
            ps = conn.prepareStatement("DELETE FROM cobertura WHERE id = ?;");
            ps.clearParameters();
            ps.setInt(1, cobertura.getId());

            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public void atualizarDado(Cobertura cobertura) {
        try {
            conn = ConnectionFactory.connection();
            ps = conn.prepareStatement("UPDATE cobertura SET id = ?, nome = ?, valor = ? "
                    + "WHERE id = ?");

            ps.clearParameters();
            criarParamatrosObjeto(cobertura);
            ps.setInt(4, cobertura.getId());

            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public boolean vereficarChave(int id) {
        try {
            conn = ConnectionFactory.connection();
            ps = conn.prepareStatement("SELECT * FROM cobertura WHERE id = ?;");
            ps.clearParameters();
            ps.setInt(1, id);

            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);

        } finally {
            ConnectionFactory.close(conn);
        }
    }

    private Cobertura gerarObjeto() throws SQLException {
        return new Cobertura(rs.getInt(1), rs.getString(2), rs.getBigDecimal(3));
    }

    private ArrayList<Cobertura> gerarLista() throws SQLException {
        ArrayList<Cobertura> coberturas = new ArrayList<>();

        while (rs.next()) {
            coberturas.add(gerarObjeto());
        }

        return coberturas;
    }

    private void criarParamatrosObjeto(Cobertura cobertura) throws SQLException {
        ps.setInt(1, cobertura.getId());
        ps.setString(2, cobertura.getNome());
        ps.setBigDecimal(3, cobertura.getValor());
    }
}
