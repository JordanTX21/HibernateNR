/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernatenr;

import java.util.List;
import modelo.Libro;
import modelo.Persona;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 *
 * @author jorda
 */
public class Parcial {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Primero creamos una persona y la asociamos con dos libros*/
        Libro libro1 = new Libro();
        libro1.setTitulo("20000 leguas de viaje submarino");
        libro1.setImagen("https://images.cdn3.buscalibre.com/fit-in/360x360/45/c0/45c076c0df67c6b572280c6bbad7fb61.jpg");
        Libro libro2 = new Libro();
        libro2.setTitulo("La maquina del tiempo");
        libro2.setImagen("https://es.web.img2.acsta.net/c_310_420/pictures/14/02/07/12/02/498665.jpg");
        Persona persona1 = new Persona();
        persona1.setNombre("Giancarlos");
        persona1.setEdad(24);
        persona1.addLibro(libro1);
        persona1.addLibro(libro2);
        /*Creamos una segunda persona, que sera eliminada, y la asociamos con otros dos libros*/
        Libro libro3 = new Libro();
        libro3.setTitulo("Spider-man, no way home");
        libro3.setImagen("http://www.tecnolack.com/wp-content/uploads/2020/12/spider-verse-747x1024-1.jpg");
        Libro libro4 = new Libro();
        libro4.setTitulo("Viaje al centro de la Tierra");
        libro4.setImagen("https://grupoalmuzara.com/libro/9788411310697_portadaweb2.jpg");
        Persona persona2 = new Persona();
        persona2.setNombre("Jordan");
        persona2.setEdad(23);
        persona2.addLibro(libro3);
        persona2.addLibro(libro4);
        /*Creamos una tercera persona y la asociamos con otros dos libros*/
        Persona persona3 = new Persona();
        persona3.setNombre("Bryan");
        persona3.setEdad(23);
        /*Creamos una tercera persona y la asociamos con otros dos libros*/
        Persona persona4 = new Persona();
        persona4.setNombre("Jeremy");
        persona4.setEdad(22);
        /*En la primer sesion guardamos las dos personas (los libros correspondientes seran guardados en cascada*/
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();
        sesion.persist(persona1);
        sesion.persist(persona2);
        sesion.persist(persona3);
        sesion.persist(persona4);
        sesion.getTransaction().commit();
        sesion.close();
        /*Realizar la buscada de Personas por sus edades pero con  un intervalo dado (de 23 a 24 años)*/
        sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.beginTransaction();
        Query q = sesion.createQuery("from Persona p WHERE p.edad BETWEEN 23 AND 24");
        List<Persona> data = q.list();
        System.out.println("*---------------------------------------*");
        for(Persona p :data){
            System.out.println("Nombre: "+p.getNombre()+" Edad: "+p.getEdad());
            if(p.getLibros().size()>0){
                System.out.println("\tLibros:");
                for(Libro l :p.getLibros()){
                    System.out.println("\tNombre: "+l.getTitulo()+" Imagen: "+l.getImagen());
                }
                System.out.println("*---------------------------------------*");
            }
        }
        sesion.close();
        
    }

}
