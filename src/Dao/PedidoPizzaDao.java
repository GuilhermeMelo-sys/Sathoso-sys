/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import pizzaria.Interfaces.Dao;
import pizzaria.Model.PedidoPizza;
import readOnly.PizzaReadOnlyPedido;

/**
 *
 * @author Guilh
 */
public class PedidoPizzaDao implements Dao<PedidoPizza> {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private String query;
    
    @Override
    public ArrayList<PedidoPizza> pegar(int idPedido) {
        conn = ConnectionFactory.connection();
        query = "SELECT*FROM pedidoPizza WHERE id_pedido = ?;";
        ArrayList<PedidoPizza> pedidos = new ArrayList<>();
        
        try {    
            ps = conn.prepareStatement(query);
            ps.clearParameters();
            ps.setInt(1, idPedido);
            rs = ps.executeQuery();
            
            while(rs.next()){
                pedidos.add(gerarObjeto());
            }
            
            return pedidos;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally{
            ConnectionFactory.close(conn, ps, rs);
        }
    }
    
    @Override
    public PedidoPizza pegarUnico(int id){
        conn = ConnectionFactory.connection();
        query = "SELECT*FROM pedidoPizza WHERE id = ?;";
        try {    
            ps = conn.prepareStatement(query);
            ps.clearParameters();
            ps.setInt(1, id);
            
            rs = ps.executeQuery();
            rs.next();
            return gerarObjeto();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally{
            ConnectionFactory.close(conn, ps, rs);
        }
    }
    
    public ArrayList<Integer> pegarIds(int idPedido) {
        conn = ConnectionFactory.connection();
        query = "SELECT*FROM pedidoPizza WHERE id_pedido = ?;";
        ArrayList<Integer> pedidosIds = new ArrayList<>();

        try {
            ps = conn.prepareStatement(query);
            ps.clearParameters();
            ps.setInt(1, idPedido);
            rs = ps.executeQuery();

            while (rs.next()) {
                pedidosIds.add(rs.getInt(1));
            }

            return pedidosIds;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn);
        }
    }

    @Override
    public ArrayList<PedidoPizza> pegarTudo() {
        conn = ConnectionFactory.connection();
        query = "SELECT*FROM pedidoPizza;";
        ArrayList<PedidoPizza> pedidos = new ArrayList<>();
        
        try {    
            ps = conn.prepareStatement(query);
            ps.clearParameters();
            rs = ps.executeQuery();
            
            while(rs.next()){
                pedidos.add(gerarObjeto());
            }
            
            return pedidos;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally{
            ConnectionFactory.close(conn, ps, rs);
        }
    }


    @Override
    public void adicionar(PedidoPizza pp) {
        conn = ConnectionFactory.connection();
        query = "INSERT INTO pedidoPizza (id_pizza, id, id_pedido, id_cobertura, quantidade_pizza, tipo_pizza)"
                + " VALUES(?, ?, ?, ?, ?, ?);";
        
        try {    
            ps = conn.prepareStatement(query);
            ps.clearParameters();
            
            ps.setInt(1, pp.getPizza().getId());
            ps.setInt(2, pp.getId());
            ps.setInt(3, pp.getPedido());
            ps.setInt(4, pp.getCobertura().getId());
            ps.setInt(5, pp.getPizza().getQuantidade());
            ps.setInt(6, pp.getPizza().getTipoPizza().indiceTipo);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally{
            ConnectionFactory.close(conn);
        }
    }

    @Override
    public void deletarDado(PedidoPizza pp) {
        conn = ConnectionFactory.connection();
        query = "DELETE FROM pedidoPizza WHERE id = ? and id_Pizza = ? and tipo_pizza = ?;";
        
        try {    
            ps = conn.prepareStatement(query);
            ps.clearParameters();
            ps.setInt(1, pp.getId());
            ps.setInt(2, pp.getPizza().getId());
            ps.setInt(3, pp.getPizza().getTipoPizza().indiceTipo);

            ps.executeUpdate();
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally{
            ConnectionFactory.close(conn);
        }
    }

    @Override
    public void atualizarDado(PedidoPizza pp) {
        conn = ConnectionFactory.connection();
        query = "UPDATE pedidoPizza "
                + "SET id_pizza = ?, quantidade_pizza = ?, tipo_pizza = ?, id_cobertura = ? "
                + "WHERE id = ?;";
        
        try {    
            ps = conn.prepareStatement(query);
            ps.clearParameters();
            
            ps.setInt(1, pp.getPizza().getId());
            ps.setInt(2, pp.getPizza().getQuantidade());
            ps.setInt(3, pp.getPizza().getTipoPizza().indiceTipo);
            ps.setInt(4, pp.getCobertura().getId());
            ps.setInt(5, pp.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        } finally{
            ConnectionFactory.close(conn);
        }
    }
    
    
    
    private PedidoPizza gerarObjeto() throws SQLException {
        PizzaReadOnlyPedido Prop = new PizzaReadOnlyPedido(new PizzaDao().
                   pegarPorTipo(rs.getInt(3), rs.getInt(6)), rs.getInt(5));
     
        return new PedidoPizza(Prop, new CoberturaDao().pegarUnico(rs.getInt(4)),
                rs.getInt(2), rs.getInt(1));
    }
    
    @Override
    public boolean vereficarChave(int id) {
        conn = ConnectionFactory.connection();
        
        try {
            query = "SELECT*FROM pedidoPizza WHERE id = ?;";
            
            ps = conn.prepareCall(query);
            ps.clearParameters();
            ps.setInt(1, id);
            
            return ps.executeQuery().isFirst();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn, ps);
        }
    }
}
