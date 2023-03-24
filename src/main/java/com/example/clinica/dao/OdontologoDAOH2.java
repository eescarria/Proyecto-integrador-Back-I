package com.example.clinica.dao;

import com.example.clinica.bd.BD;
import com.example.clinica.domain.Odontologo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OdontologoDAOH2 implements IDao<Odontologo> {

    private static final Logger logger = Logger.getLogger(OdontologoDAOH2.class);

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;

        try {
            connection = BD.getConnection();

            PreparedStatement ps = connection.prepareStatement("INSERT INTO ODONTOLOGOS(" +
                    "NUM_MATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, odontologo.getNumeroMatricula());
            ps.setString(2, odontologo.getNombre());
            ps.setString(3, odontologo.getApellido());

            ps.execute();
            logger.debug("Se ha conectado a la base de datos");

            ResultSet clave = ps.getGeneratedKeys();
            while (clave.next()){
                odontologo.setId(clave.getInt(1));
            }

            logger.debug("Se ha guardado el odontólogo con ID: " + odontologo.getId());

        }catch (Exception e){
            e.printStackTrace();
            logger.error("Se ha generado error con la conexión a la base de datos");
        }finally {
            try {
                connection.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return odontologo;

    }

    @Override
    public Odontologo buscar(int id) {
        Connection connection = null;

        Odontologo odontologo= null;
        try {
            connection = BD.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ODONTOLOGOS WHERE ID = ?");
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();


            while (rs.next()){
                odontologo = new Odontologo(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));
            }
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            try {
                connection.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return odontologo;


    }

    @Override
    public void eliminar(int id) {
        Connection connection = null;
        try {
            connection = BD.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM ODONTOLOGOS WHERE ID= ?");
            ps.setInt(1, id);
            ps.execute();
            logger.info("Se ha eliminado el odontólogo con ID: " +id);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        Connection connection = null;
        try {
            connection = BD.getConnection();

            PreparedStatement ps = connection.prepareStatement("UPDATE ODONTOLOGOS SET NUM_MATRICULA=?," +
                    "NOMBRE = ?, APELLIDO = ? WHERE ID = ?");
            ps.setString(1, odontologo.getNumeroMatricula());
            ps.setString(2, odontologo.getNombre());
            ps.setString(3, odontologo.getApellido());
            ps.setInt(4, odontologo.getId());

            ps.execute();
            logger.info("Se ha actualizado el odontólogo con ID " + odontologo.getId());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        Connection connection = null;

        List<Odontologo> odontologoList = new ArrayList<>();

        try {
            connection = BD.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet rs = preparedStatement.executeQuery();

            logger.debug("Se ha conectado a la base de datos");

            while (rs.next()){

                int id = rs.getInt("id");
                String numMatricula = rs.getNString("num_matricula");
                String nombre = rs.getNString("nombre");
                String apellido = rs.getNString("apellido");

                odontologoList.add(new Odontologo(id,numMatricula,nombre,apellido));

            }
            logger.debug("Se ha generado el listado de todos los odontólogos, en total son: " + odontologoList.size());
            preparedStatement.close();


        }catch (Exception e){
            e.printStackTrace();
            logger.error("Se ha generado error con la conexión a la base de datos");

        }finally {
            try {
                connection.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return odontologoList;
    }

    @Override
    public Odontologo buscarXCriterioString(String criterio) {
        return null;
    }

}
