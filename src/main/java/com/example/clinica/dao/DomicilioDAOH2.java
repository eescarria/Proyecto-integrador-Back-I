package com.example.clinica.dao;

import com.example.clinica.bd.BD;
import com.example.clinica.domain.Domicilio;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DomicilioDAOH2 implements IDao<Domicilio>{
    @Override
    public Domicilio guardar(Domicilio domicilio) {
        Connection connection = null;
        try {
            connection = BD.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO DOMICILIOS(" +
                    "CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, domicilio.getCalle());
            ps.setString(2, domicilio.getNumero());
            ps.setString(3, domicilio.getLocalidad());
            ps.setString(4, domicilio.getProvincia());
            ps.execute();

            ResultSet clave = ps.getGeneratedKeys();
            while (clave.next()){
                domicilio.setId(clave.getInt(1));
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
        return domicilio;
    }

    @Override
    public Domicilio buscar(int id) {
        Connection connection = null;
        Domicilio domicilio = null;
        try {
            connection = BD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM DOMICILIOS WHERE ID = ?");
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){

                int idBuscado = rs.getInt(1);
                String calle = rs.getString(2);
                String numero = rs.getString(3);
                String localidad = rs.getString(4);
                String provincia = rs.getString(5);

                domicilio = new Domicilio(idBuscado,calle,numero,localidad,provincia);
            }

            preparedStatement.close();


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return domicilio;
    }

    @Override
    public void eliminar(int id) {
        Connection connection = null;

        try {
            connection = BD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM DOMICILIOS WHERE ID = ?");
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            preparedStatement.close();

            if (rowsDeleted > 0) {
                System.out.println("El objeto con ID " + id + " fue eliminado de la base de datos.");
            } else {
                System.out.println("No se pudo eliminar el objeto con ID " + id + ".");
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

    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) {
        Connection connection = null;

        try {
            connection = BD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE DOMICILIOS SET CALLE=?, NUMERO=?, LOCALIDAD=?, PROVINCIA=? WHERE ID = ?");
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setString(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());
            preparedStatement.setInt(5, domicilio.getId());

            int rowsUpdated = preparedStatement.executeUpdate();
            preparedStatement.close();

            if (rowsUpdated > 0) {
                System.out.println("El objeto con ID " + domicilio.getId() + " fue actualizado en la base de datos.");
            } else {
                System.out.println("No se pudo actualizar el objeto con ID " + domicilio.getId() + ".");
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
        return domicilio;

    }

    @Override
    public List<Domicilio> buscarTodos() {
        Connection connection = null;
        List<Domicilio> domicilioList = new ArrayList<>();
        try {
            connection = BD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM DOMICILIOS");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){

                int id = rs.getInt("id");
                String calle = rs.getNString("calle");
                String numero = rs.getNString("numero");
                String localidad = rs.getNString("localidad");
                String provincia = rs.getNString("provincia");

                domicilioList.add(new Domicilio(id,calle,numero,localidad,provincia));

            }

            preparedStatement.close();


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return domicilioList;
    }

    @Override
    public Domicilio buscarXCriterioString(String criterio) {
        return null;
    }
}
