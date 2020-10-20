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
import javax.swing.JOptionPane;
import pizzaria.Interfaces.Dao;
import pizzaria.Model.Pedido;
import readOnly.PedidoReadOnly;

/**
 *
 * @author Guilh
 */
public class PedidoDao implements Dao<Pedido> {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private String query;

    @Override
    public ArrayList<Pedido> pegar(int id) {
        conn = ConnectionFactory.connection();
        ArrayList<Pedido> lstPdro = new ArrayList<>();
        try {
            query = "SELECT*FROM pedido WHERE id = ?;";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            rs.next();
            lstPdro.add(gerarObjeto());

            return lstPdro;
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível encontrar o objeto!");
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    public ArrayList<Pedido> pegar(boolean estado) {
        conn = ConnectionFactory.connection();
        ArrayList<Pedido> lstPdro = new ArrayList<>();
        try {
            query = "SELECT*FROM pedido WHERE estado = ?;";
            ps = conn.prepareStatement(query);

            ps.setBoolean(1, estado);
            rs = ps.executeQuery();

            while (rs.next()) {
                lstPdro.add(gerarObjeto());
            }

            return lstPdro;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Valor não encontrado!");
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }
    
    public Pedido pegarUnico(int id){
        conn = ConnectionFactory.connection();
        System.out.println(id);
        try {
            query = "SELECT*FROM pedido WHERE id = ?;";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            rs.next();
            return gerarObjeto();
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível encontrar o objeto!");
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public ArrayList<Pedido> pegarTudo() {
        conn = ConnectionFactory.connection();
        ArrayList<Pedido> lstPdro = new ArrayList<>();

        try {
            query = "SELECT*FROM pedido;";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                lstPdro.add(gerarObjeto());
            }
            return lstPdro;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public void adicionar(Pedido pdro) {
        conn = ConnectionFactory.connection();
        query = "INSERT INTO pedido (id, id_endereco,  "
                + "valor_final, estado) "
                + "VALUES (?, ?, ?, ?);";
        try {

            ps = conn.prepareStatement(query);
            ps.clearParameters();
            ps.setInt(1, pdro.getId());
            ps.setInt(2, pdro.getEndereco().getId());
            ps.setBigDecimal(3, pdro.getValorTotal());
            ps.setBoolean(4, pdro.getStatus());

            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn, ps);
        }
    }

    @Override
    public void deletarDado(Pedido pdro) {
        conn = ConnectionFactory.connection();
        query = "DELETE FROM pedido WHERE id = ? and id_endereco = ?;";
        try {

            ps = conn.prepareStatement(query);
            ps.setInt(1, pdro.getId());
            ps.setInt(2, pdro.getEndereco().getId());

            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn, ps);
        }
    }

    @Override
    public void atualizarDado(Pedido pdro) {
        conn = ConnectionFactory.connection();
        query = "UPDATE pedido "
                + "SET id_endereco = ?, valor_final = ?, estado = ? "
                + "WHERE id = ?;";
        System.out.println(pdro.toString());
        try {
            ps = conn.prepareStatement(query);
            ps.clearParameters();
            
            System.out.println(pdro.getEndereco());
            ps.setInt(1, pdro.getEndereco().getId());
            ps.setBigDecimal(2, pdro.getValorTotal());
            ps.setBoolean(3, pdro.getStatus());
            ps.setInt(4, pdro.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    public boolean verificarChave(int id) {
        conn = ConnectionFactory.connection();

        try {
            ps = conn.prepareStatement("SELECT*FROM pedido WHERE id = ?;");
            ps.clearParameters();
            ps.setInt(1, id);

            rs = ps.executeQuery();
            
            return rs.next();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn, ps);
        }
    }

    private PedidoReadOnly gerarObjeto() throws SQLException {

        return new PedidoReadOnly(rs.getInt(1),
                new EnderecoDao().pegarUnico(rs.getInt(2)),
                rs.getBigDecimal(3),
                rs.getBoolean(4));
    }

    @Override
    public boolean vereficarChave(int id) {
        conn = ConnectionFactory.connection();

        try {
            query = "SELECT*FROM pedido WHERE id = ?;";

            ps = conn.prepareStatement(query);
            ps.clearParameters();
            ps.setInt(1, id);
            rs = ps.executeQuery();

            return rs.isFirst();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

}
