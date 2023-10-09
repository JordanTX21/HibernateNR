/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import modelo.Empleado;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author jorda
 */
public class EmpleadosDAO {

    private Session sesion;
    private Transaction tx;

    public String guardaEmpleado(Empleado Empleado) throws HibernateException {
        String id = "codigo";
        try {
            iniciaOperacion();
            id = (String) sesion.save(Empleado);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
        return id;
    }

    public void actualizaEmpleado(Empleado Empleado) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.update(Empleado);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    public void eliminaEmpleado(Empleado Empleado) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.delete(Empleado);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    public Empleado obtenEmpleado(String idEmpleado) throws HibernateException {
        Empleado Empleado = null;
        try {
            iniciaOperacion();
            Empleado = (Empleado) sesion.get(Empleado.class, idEmpleado);
        } finally {
            sesion.close();
        }
        return Empleado;
    }

    public List<Empleado> obtenListaEmpleados() throws HibernateException {
        List<Empleado> listaEmpleados = null;
        try {
            iniciaOperacion();
            listaEmpleados = sesion.createQuery("from Empleado").list();
        } finally {
            sesion.close();
        }
        return listaEmpleados;
    }

    private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió error en capa de acceso a datos", he);
    }
}
