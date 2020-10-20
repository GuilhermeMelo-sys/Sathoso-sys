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
import pizzaria.Model.Endereco;

/**
 *
 * @author Guilh
 */
public class EnderecoDao implements Dao<Endereco> {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private String query;

    @Override
    public ArrayList<Endereco> pegar(int id) {
        conn = ConnectionFactory.connection();
        ArrayList<Endereco> lstEro = new ArrayList<>();

        try {
            ps = conn.prepareStatement("SELECT*FROM pedido WHERE id = ?;");
            ps.clearParameters();
            
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                lstEro.add(gerarObjeto());
            }
            
            return lstEro;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    public Endereco pegarUnico(int id) {
        conn = ConnectionFactory.connection();

        try {
            ps = conn.prepareStatement("SELECT*FROM endereco WHERE id = ?;");
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

    public ArrayList<Endereco> pegarPelaCidade(String cidade){
        conn = ConnectionFactory.connection();
        ArrayList<Endereco> lstEro = new ArrayList<>();
        
        try{
            ps = conn.prepareStatement("SELECT*FROM endereco where cidade like ?;");
            ps.clearParameters();
            
            ps.setString(1, cidade+ '%' );
            
            rs = ps.executeQuery();
            
            while(rs.next()) lstEro.add(gerarObjeto());
            
            return lstEro;
        } catch(SQLException ex){
            throw new RuntimeException(ex);
        } finally{
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public ArrayList<Endereco> pegarTudo() {
        conn = ConnectionFactory.connection();
        ArrayList<Endereco> lstEro = new ArrayList<>();
        
        try {
            ps = conn.prepareStatement("SELECT * FROM endereco;");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                lstEro.add(gerarObjeto());
            }
            
            return lstEro;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public void adicionar(Endereco ero) {
        query = "INSERT INTO endereco (id, cidade, logradouro, bairro) VALUES(?, ?, ?, ?);";
        conn = ConnectionFactory.connection();

        try {
            ps = conn.prepareStatement(query);
            ps.clearParameters();
            
            ps.setInt(1, ero.getId());
            ps.setString(2, ero.getCidade());
            ps.setString(3, ero.getLogradouro());
            ps.setString(4, ero.getBairro());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn, ps);
        }
    }

    @Override
    public void deletarDado(Endereco ero) {
        conn = ConnectionFactory.connection();

        try {
            query = "DELETE * FROM endereco WHERE id = ?;";
            
            ps = conn.prepareStatement(query);
            ps.clearParameters();
            
            ps.setInt(1, ero.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn, ps);
        }
    }

    @Override
    public void atualizarDado( Endereco ero) {
        conn = ConnectionFactory.connection();

        try {
            query = "UPDATE endereco "
                    + "SET cidade = ?, logradouro = ?, bairro = ? "
                    + "WHERE id = ?;";
            
            ps = conn.prepareStatement(query);
            ps.clearParameters();
            
            ps.setString(1, ero.getCidade());
            ps.setString(2, ero.getLogradouro());
            ps.setString(3, ero.getBairro());
            ps.setInt(4, ero.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn, ps);
        }
    }

    private Endereco gerarObjeto() throws SQLException {
        return new Endereco(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
    }

    @Override
    public boolean vereficarChave(int id) {
        conn = ConnectionFactory.connection();
        
        try {
           query = "SELECT*FROM pizza WHERE id = ?;";
            
            ps = conn.prepareStatement(query);
            ps.clearParameters();
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            return rs.isFirst();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally{
            ConnectionFactory.close(conn, ps, rs);
        }
    }
}
