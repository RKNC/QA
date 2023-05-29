package bdMYSQL;

import Clases.Usuario;
import java.sql.*;
import javax.swing.JOptionPane;
        
public class SqlUsers extends ConexionBD{
    
    public boolean registrar (Usuario usr){
        PreparedStatement ps = null;
        int id = -1;
        ResultSet rs = null;
        String sql = "INSERT INTO persona (nombre, apellido, dni, telefono, correo) VALUES (?, ?, ?, ?, ?)";
        try{
            
            ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1,usr.getNombre());
            ps.setString(2,usr.getApellido());
            ps.setString(3,usr.getDni());
            ps.setString(4,usr.getTelefono());
            ps.setString(5,usr.getCorreo());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            usr.setIdPersona(id);
            registrarUsuario (usr);
            return true;
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al registrar.\n" + ex);
            return false;
        }
    }
    
    public boolean registrarUsuario (Usuario usr) throws SQLException{
        PreparedStatement ps = null;
        String sql = "INSERT INTO usuario (id_persona, correo, contrasena) VALUES (?, ?, ?)";
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, usr.getIdPersona());
            ps.setString(2,usr.getCorreo());
            ps.setString(3,usr.getContraseña());
            ps.executeUpdate();
            return true;
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al registrar.\n" + ex);
            return false;
        }
    }

    public int obtenerId (String correo, String contraseña) throws SQLException{
        int id = -1;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            Connection con = conectar();
            String sql = "SELECT id_persona FROM usuario WHERE correo = ? AND contrasena = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();
            
            if (rs.next())
               id = rs.getInt(1);
        }catch (SQLException ex){
        }
        return id;
    }
    
    public boolean ingresar (Usuario usr) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = obtenerId (usr.getCorreo(), usr.getContraseña());
        if (id == -1){
            return false;
        }
        else{
            String sql = "SELECT nombre, apellido, dni, telefono, correo FROM persona WHERE id = ?";
            try{
                Connection con = conectar();
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();

                if (rs.next()){
                    usr.setNombre (rs.getString(1));
                    usr.setApellido(rs.getString(2));
                    usr.setDni(rs.getString(3));
                    usr.setTelefono(rs.getString(4));
                    usr.setCorreo(rs.getString(4));
                    return true;
                }else
                    return false;
            }catch (SQLException ex){
                return false;
             }
        }
    }
    
    public int existe (String usuario) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT count(id_usuario) FROM usuario WHERE correo = ?";
        
        try{
            Connection con = conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            
            if (rs.next()){
                return rs.getInt(1);
            }
            return 1;
        }catch (SQLException ex){
            return 1;
         }
    }
}
