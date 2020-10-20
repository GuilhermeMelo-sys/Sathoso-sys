    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Enum.PizzasEnum;
import Exceptions.NaoCadastroException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pizzaria.Interfaces.Dao;
import pizzaria.Interfaces.Relatorio;
import readOnly.PizzaReadOnly;

/**
 *
 * @author Guilh
 */
public final class PizzaDao implements Dao<PizzaReadOnly>, Relatorio<PizzaReadOnly> {

    protected Connection conn;
    protected PreparedStatement ps;
    protected ResultSet rs;

    public PizzaDao() {
        if(!RELATORIO.isEmpty()) resetarLogs();
    }
 
    @Override
    public ArrayList<PizzaReadOnly> pegar(int id) {
        conn = ConnectionFactory.connection();
        ArrayList<PizzaReadOnly> lstPro = new ArrayList<>();
        String query = "SELECT * FROM pizza WHERE ID = ?;";
        
        try {
            if (!this.verificarSeExisteDado(id)) {
                throw new NaoCadastroException("Não existe dado cadastrado nesse Id.");
            }
            
            ps = conn.prepareStatement(query);
            ps.clearParameters();
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                lstPro.add(gerarObjeto());
            }
            
            return lstPro;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }
    
    public PizzaReadOnly pegarUnico(int id) {
        conn = ConnectionFactory.connection();
        String query = "SELECT * FROM pizza WHERE ID = ?;";
        
        try {
            if (!this.verificarSeExisteDado(id)) {
                throw new NaoCadastroException("Não existe dado cadastrado nesse Id.");
            }

            ps = conn.prepareStatement(query);
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
    
    public PizzaReadOnly pegarPorTipo(PizzaReadOnly pro){
        try {
            if (VerificarSeExisteDado(pro)) {
                throw new NaoCadastroException("Não existe dado cadastrado nesse Id.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PizzaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pegarPorTipo(pro.getId(), pro.getTipoPizza().indiceTipo);
    }
    
    public PizzaReadOnly pegarPorTipo(int idPizza, int tipoPizza) {
        conn = ConnectionFactory.connection();
        String query = "SELECT * FROM pizza WHERE ID = ? AND tipo_pizza = ?;";
        System.out.println("ID: "+ idPizza +" -- Tipo: "+ tipoPizza);
        try {
            ps = conn.prepareStatement(query);
            ps.clearParameters();
            ps.setInt(1, idPizza);
            ps.setInt(2, tipoPizza);
            rs = ps.executeQuery();
            
            rs.next();
            return gerarObjeto();
        } catch (SQLException ex) {System.out.println("Deu ruim");
            throw new RuntimeException(ex);
        } finally {
            System.out.println("Terminei");
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public ArrayList<PizzaReadOnly> pegarTudo() {
        conn = ConnectionFactory.connection();
        ArrayList<PizzaReadOnly> lstPro = new ArrayList<>();
        String query = "SELECT * FROM pizza ORDER BY id;";

        try {
            if (!verificarSeExisteDados()) {
                throw new NaoCadastroException("Não existe Dado cadastrado");
            }

            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                lstPro.add(gerarObjeto());
            }
            
            return lstPro;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }
    
    @Override
    public void adicionar(PizzaReadOnly pizza) {
        String query = "INSERT INTO pizza (id, sabor, tipo_pizza, valor) VALUES(?, ?, ?, ?);";
        conn = ConnectionFactory.connection();

        try {
            if (pizza.getTipoPizza().name().toLowerCase().equals("individual") 
                    && VerificarSeExisteDado(pizza)) {
                return;
            }
            if (VerificarSeExisteDado(pizza)) {
                RELATORIO.add(pizza);
                return;
            }
            
            ps = conn.prepareStatement(query);
            ps.clearParameters();
            ps.setInt(1, pizza.getId());
            ps.setString(2, pizza.getSabor().trim());
            ps.setInt(3, pizza.getTipoPizza().indiceTipo);
            ps.setBigDecimal(4, pizza.getValor());

            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn, ps);
        }
    }

    @Override
    public void atualizarDado(PizzaReadOnly pizza) {
        String query = "UPDATE pizzaria "
                + "SET sabor = ?, tipo_pizza = ?, valor = ? "
                + "WHERE id = ?;";
        conn = ConnectionFactory.connection();
        
        try {
            if (!(this.verificarSeExisteDado(pizza.getId()))) {
                throw new NaoCadastroException("Dado inexistente!");
            }
            
            ps = conn.prepareStatement(query);
            ps.clearParameters();
            ps.setString(1, pizza.getSabor());
            ps.setInt(2, pizza.getTipoPizza().indiceTipo);
            ps.setBigDecimal(3, pizza.getValor());
            ps.setInt(4, pizza.getId());

            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    @Override
    public void deletarDado(PizzaReadOnly pro) {
        String query = "DELETE * FROM pizza WHERE id = ?";
        conn = ConnectionFactory.connection();
        
        try {
            ps = conn.prepareStatement(query);
            ps.clearParameters();
            ps.setInt(1, pro.getId());

            ps.executeUpdate(query);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }
    
    public ArrayList<PizzaReadOnly> pegarDadosAgrupados() {
        String query = "SELECT  id, sabor, tipo_pizza, valor FROM pizza GROUP BY id ORDER BY id asc;";
        ArrayList<PizzaReadOnly> lstPro = new ArrayList<>();
        conn = ConnectionFactory.connection();
        
        try {
            ps = conn.prepareStatement(query);
            ps.clearParameters();
            rs = ps.executeQuery();
            
            while(rs.next()){
                lstPro.add(gerarObjeto());
            }
            
            return lstPro;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionFactory.close(conn, ps, rs);
        }
    }

    private Boolean VerificarSeExisteDado(PizzaReadOnly pizza) throws SQLException {
        String query = "SELECT * FROM pizza WHERE ID = ? AND tipo_pizza = ?;";
        ps = conn.prepareStatement(query);

        ps.clearParameters();
        ps.setInt(1, pizza.getId());
        ps.setInt(2, pizza.getTipoPizza().indiceTipo);

        rs = ps.executeQuery();
        return rs.next();
    }

    private Boolean verificarSeExisteDado(int id) throws SQLException {
        ps = conn.prepareStatement("SELECT * FROM pizza WHERE  id = ?;");
        ps.setInt(1, id);
        rs = ps.executeQuery();
        return rs.next();
    }
    
    private Boolean verificarSeExisteDados() throws SQLException{
        ps = conn.prepareStatement("SELECT * FROM pizza;");
        rs = ps.executeQuery();
        return rs.next();
    }

    private PizzaReadOnly gerarObjeto() throws SQLException {
        return new PizzaReadOnly(rs.getInt(1), PizzasEnum.values()[rs.getInt(3)], rs.getString(2),
                rs.getBigDecimal(4));
    }
    
    @Override
    public boolean vereficarChave(int id) {
        conn = ConnectionFactory.connection();
        
        try {
            String query = "SELECT*FROM pizza WHERE id = ?;";
            
            ps = conn.prepareStatement(query);
            ps.clearParameters();
            ps.setInt(1, id);
            rs = ps.executeQuery();
            return rs.isFirst();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ArrayList<PizzaReadOnly> pegarLogs() {
        ArrayList<PizzaReadOnly> lstPro = new ArrayList<>();
        RELATORIO.forEach(c -> lstPro.add((PizzaReadOnly) c));
        return lstPro;
    }

    @Override
    public void resetarLogs() {
        RELATORIO.clear();
    }
}
