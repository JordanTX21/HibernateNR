/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pruebas;

import DAO.EmpleadosDAO;
import java.util.List;
import modelo.Empleado;
import servicio.Parsea;

/**
 *
 * @author jorda
 */
public class Prueba {

    public static void main(String[] args) {
        EmpleadosDAO EmpleadosDAO = new EmpleadosDAO();
        Empleado EmpleadoRecuperado = null;
        String idAEliminar = "codigo";
        // Crea el objeto para parsear dos datos del formulario, tipos String a tipo Date -fecha- y a
        //tipo double -salario-.
        Parsea ps = new Parsea();
        java.sql.Date fechaParseado = ps.stringDate("2010-11-10");
        double salarioParseado = ps.stringDouble("283.2");
        //Creamos tes instancias de Empleado
        Empleado Empleado1 = new Empleado("Empleado 1", "Nombre1Empleado",
                "Apellido1Empleado", fechaParseado, salarioParseado);
        Empleado Empleado2 = new Empleado("Empleado 2", "Nombre2Empleado",
                "Apellido2Empleado", fechaParseado, salarioParseado);
        Empleado Empleado3 = new Empleado("Empleado 3", "Nombre3Empleado",
                "Apellido3Empleado", fechaParseado, salarioParseado);
        //Guardamos las tres instancias, guardamos el id del Empleado1 para usarlo posteriormente
        idAEliminar = EmpleadosDAO.guardaEmpleado(Empleado1);
        EmpleadosDAO.guardaEmpleado(Empleado2);
        EmpleadosDAO.guardaEmpleado(Empleado3);
        //Modificamos el Empleado 2 y lo actualizamos
        Empleado2.setNombre("Nuevo Empleado 2");
        EmpleadosDAO.actualizaEmpleado(Empleado2);
        //Recuperamos el Empleado1 de la base de datos
        EmpleadoRecuperado = EmpleadosDAO.obtenEmpleado(idAEliminar);
        System.out.println("Recuperamos a " + EmpleadoRecuperado.getNombre());
        //Eliminamos al EmpleadoRecuperado (que es el Empleado1)
        EmpleadosDAO.eliminaEmpleado(EmpleadoRecuperado);
        //Obtenemos la lista de Empleados que quedan en la base de datos y la mostramos
        List<Empleado> listaEmpleados = EmpleadosDAO.obtenListaEmpleados();
        System.out.println("Hay " + listaEmpleados.size() + " Empleados en la base de datos");
        for (Empleado c : listaEmpleados) {
            System.out.println("-> " + c.getNombre());
        }
    }
}
