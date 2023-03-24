package com.example.clinica.dao;

import com.example.clinica.bd.BD;
import com.example.clinica.domain.Domicilio;
import com.example.clinica.domain.Paciente;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PacienteDAOH2 implements IDao<Paciente> {
    private static final Logger logger = Logger.getLogger(PacienteDAOH2.class);

    @Autowired
    private DomicilioDAOH2 domicilioDAOH2;


    @Override
    public Paciente guardar(Paciente paciente) {
        Connection connection = null;
        try {
            connection = BD.getConnection();
            logger.info("Conexión OK a la base de datos");

            Domicilio domicilio = domicilioDAOH2.guardar(paciente.getDomicilio());

            PreparedStatement ps = connection.prepareStatement("INSERT INTO PACIENTES (APELLIDO, NOMBRE, " +
                    "DOCUMENTO, FECHA_INGRESO, DOMICILIO_ID, EMAIL) VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, paciente.getApellido());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, paciente.getDocumento());
            ps.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            ps.setInt(5,domicilio.getId());
            ps.setString(6,paciente.getEmail());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()){
                paciente.setId(rs.getInt(1));
            }
            logger.info("Se ha guardado el paciente con ID: " + paciente.getId());

        }catch (Exception e){
            logger.error("Error conectando a la base de datos");
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return paciente;
    }

    @Override
    public Paciente buscar(int id) {
        Connection connection = null;
        Paciente paciente = null;
        try {
            connection = BD.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM PACIENTES WHERE ID = ?");
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                paciente = new Paciente(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getDate(5).toLocalDate(),domicilioDAOH2.buscar(rs.getInt(6)),
                        rs.getString(7));
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
        return paciente;

    }

    @Override
    public void eliminar(int id) {
        Connection connection = null;
        try {
            connection = BD.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM PACIENTES WHERE ID= ?");
            ps.setInt(1, id);
            ps.execute();
            logger.info("Se ha eliminado el paciente con ID: " +id);

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
    public Paciente actualizar(Paciente paciente) {
        Connection connection = null;
        try {
            connection = BD.getConnection();

            PreparedStatement ps = connection.prepareStatement("UPDATE PACIENTES SET APELLIDO=?," +
                            "NOMBRE = ?, DOCUMENTO = ?, FECHA_INGRESO = ?, DOMICILIO_ID=?, EMAIL=? WHERE ID = ?");
            ps.setString(1, paciente.getApellido());
            ps.setString(2, paciente.getNombre());
            ps.setString(3, paciente.getDocumento());
            ps.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            ps.setInt(5, paciente.getDomicilio().getId());
            ps.setString(6,paciente.getEmail());
            ps.setInt(7, paciente.getId());

            ps.execute();
            logger.info("Se ha actualizado el paciente con ID " + paciente.getId());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return paciente;
    }

    @Override
    public List<Paciente> buscarTodos() {
        Connection connection = null;
        List<Paciente> pacienteList = new ArrayList<>();
        try {
            connection = BD.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM PACIENTES");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                pacienteList.add(new Paciente(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getDate(5).toLocalDate(),domicilioDAOH2.buscar(rs.getInt(6)),
                        rs.getString(7)));
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
        return pacienteList;
    }

    @Override
    public Paciente buscarXCriterioString(String criterio) {
        Connection connection = null;
        Paciente paciente = null;
        try {
            connection = BD.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM PACIENTES WHERE EMAIL = ?");
            ps.setString(1,criterio);

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                paciente = new Paciente(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getDate(5).toLocalDate(),domicilioDAOH2.buscar(rs.getInt(6)),
                        rs.getString(7));
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
        return paciente;

    }
}