
import java_cup.runtime.Symbol;

import java.util.Scanner;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Factura {
    String descripcion;
    int precio;
    int cantidadProducto;
    java.util.Date fechaFactura;
    int codigo;

    Factura(String descripcion, int precio, int cantidadProducto, java.util.Date fechaFactura, int codigo) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadProducto = cantidadProducto;
        this.fechaFactura = fechaFactura;
        this.codigo = codigo;
    }

    int getImporte() {
        return precio;

    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCodigo() {
        return codigo;
    }

    int getCantidadProducto() {
        return cantidadProducto;
    }

    java.util.Date getFechaFactura() {
        return fechaFactura;
    }
}

public class main {
    public static int cantidad = 0;
    public static int codigo = 0;
    public static java.util.Date fechafecha;

    public static void main(String[] args) throws ParseException {
        java.util.Date hoy = new java.util.Date();
        String fechita = "01/01/2021";
        SimpleDateFormat formatedate = new SimpleDateFormat("dd/MM/yyyy");
        hoy = formatedate.parse(fechita);

        String fechaa = "29/09/1988";
        Scanner sc = new Scanner(System.in);

        Factura f = new Factura("ordenador", 1000, 20, hoy, 1);
        Factura f2 = new Factura("movil", 300, 20, hoy, 2);
        Factura f3 = new Factura("impresora", 200, 30, hoy, 3);
        Factura f4 = new Factura("imac", 1500, 40, hoy, 4);
        List<Factura> lista = new ArrayList<Factura>();

        lista.add(f);
        lista.add(f2);
        lista.add(f3);
        lista.add(f4);

        Predicate<Factura> predicadofechaanterior = new Predicate<Factura>() {
            @Override
            public boolean test(Factura t) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                System.out.println("iteracion ");
                return t.fechaFactura.before(fechafecha);
            }
        };
        Predicate<Factura> predicadocantidad = new Predicate<Factura>() {
            @Override
            public boolean test(Factura t) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                // System.out.println("iteracion ");
                return t.getCantidadProducto() == cantidad;
            }
        };
        Predicate<Factura> predicadocodigo = new Predicate<Factura>() {
            @Override
            public boolean test(Factura t) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                // System.out.println("iteracion ");
                return t.getCantidadProducto() == cantidad;
            }
        };
        Predicate<Factura> predicadocantidadmayor = new Predicate<Factura>() {
            @Override
            public boolean test(Factura t) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //  System.out.println("iteracion ");
                return t.getCantidadProducto() > cantidad;
            }
        };
        Predicate<Factura> predicadocantidadmenor = new Predicate<Factura>() {
            @Override
            public boolean test(Factura t) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //System.out.println("iteracion ");
                return t.getCantidadProducto() < cantidad;
            }
        };
        Predicate<Factura> predicadofechaposterior = new Predicate<Factura>() {
            @Override
            public boolean test(Factura t) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                //  System.out.println("iteracion ");
                return t.fechaFactura.after(fechafecha);
            }
        };


        //   cantidad=sc.nextInt();


        int opcion = 0;
        String fechaenstring = "";

        do {
            System.out.println("ingrese opcion"+
            "1 - buscar por cantidad exacta (con predicado) \n" +
                    "2 - buscar por fecha posterior (con predicado) \n" +
                    "3 - buscar por fecha anterior (con predicado) \n" +
                    "4 - buscar por cantidad mayor (con predicado) \n" +
                    "5 - buscar por cantidad menor (con predicado) \n" +
                    "6 - buscar por codigo (con predicado)" +
                    "7 - buscar por cantidad exacta (sin predicado) \n" +
                    "8 - buscar por fecha posterior (sin predicado) \n" +
                    "9 - buscar por fecha anterior (sin predicado) \n" +
                    "10 - buscar por cantidad mayor (sin predicado) \n" +
                    "11 - buscar por cantidad menor (sin predicado) \n" +
                    "12 - buscar por codigo (sin predicado)");
            System.out.print("OPCION: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("ingrese cantidad de productos");
                    cantidad = sc.nextInt();
                    List<Factura> facturaFiltro = lista.stream()
                            .filter(predicadocantidad).collect(Collectors.toList());
                    for (Factura fac : facturaFiltro) {
                        System.out.println("Descripcion producto:" + fac.getDescripcion() + " cantidad:" + fac.getCantidadProducto());
                    }

                    break;
                case 2:
                    Scanner sc2 = new Scanner(System.in);
                    System.out.println("ingrese fecha los productos posteriores a la misma se mostraran");
                    System.out.print("Fecha (Dia/Mes/año) ej : 12/10/2020.. :");
                    fechaenstring = sc2.nextLine();
                    System.out.println(fechaenstring + " fechaenstring es");
                    fechafecha = formatedate.parse(fechaenstring);
                    List<Factura> facturaFiltro2 = lista.stream()
                            .filter(predicadofechaposterior).collect(Collectors.toList());
                    for (Factura fac : facturaFiltro2) {
                        System.out.println("la factura es del producto " + fac.getDescripcion() + " y su fecha es " + formatedate.format(fac.getFechaFactura()));
                    }

                    break;
                case 3:
                    Scanner sc3 = new Scanner(System.in);
                    System.out.println("ingrese fecha los productos anteriores a la misma se mostraran");
                    System.out.print("Fecha (Dia/Mes/año) ej : 12/10/2020.. :");
                    fechaenstring = sc3.nextLine();
                    System.out.println(fechaenstring + " fechaenstring es");
                    fechafecha = formatedate.parse(fechaenstring);
                    List<Factura> facturaFiltro4 = lista.stream()
                            .filter(predicadofechaanterior).collect(Collectors.toList());
                    for (Factura facx : facturaFiltro4) {
                        System.out.println("la factura es del producto " + facx.getDescripcion() + " y su fecha es " + formatedate.format(facx.getFechaFactura()));
                    }
                    break;
                case 4:
                    System.out.println("ingrese una cantidad cantidad para buscar productos con cantidad mayor a la ingresada");
                    cantidad = sc.nextInt();
                    List<Factura> facturaFiltro22 = lista.stream()
                            .filter(predicadocantidadmayor).collect(Collectors.toList());
                    for (Factura fac : facturaFiltro22) {
                        System.out.println("Descripcion producto:" + fac.getDescripcion() + " cantidad:" + fac.getCantidadProducto());
                    }
                    break;
                case 5:
                    System.out.println("ingrese cantidad de productos");
                    cantidad = sc.nextInt();
                    System.out.println("ingrese una cantidad para buscar productos con cantidad menor a la ingresada");
                    cantidad = sc.nextInt();
                    List<Factura> facturaFiltro38 = lista.stream()
                            .filter(predicadocantidadmenor).collect(Collectors.toList());
                    for (Factura fac : facturaFiltro38) {
                        System.out.println("Descripcion producto:" + fac.getDescripcion() + " cantidad:" + fac.getCantidadProducto());
                    }
                    break;
                case 6:
                    System.out.println("ingrese cantidad de productos");
                    cantidad = sc.nextInt();
                    System.out.println("ingrese un codigo para buscar productos con el mismo codigo");
                    cantidad = sc.nextInt();
                    List<Factura> facturaFiltro35 = lista.stream()
                            .filter(predicadocodigo).collect(Collectors.toList());
                    for (Factura fac : facturaFiltro35) {
                        System.out.println("Descripcion producto:" + fac.getDescripcion() + " Codigo:" + fac.getCodigo());
                    }
                    break;
                case 7:


                    System.out.println("ingrese cantidad de productos");
                    cantidad = sc.nextInt();
                    List<Factura> facturaFiltroX = lista.stream()
                            .filter(elemento -> elemento.getCantidadProducto() == cantidad).collect(Collectors.toList());
                    for (Factura o : facturaFiltroX) {
                        System.out.println("la factura es del producto " + o.getDescripcion() + " y su cantidad es: " +o.getCantidadProducto());
                    }
                    break;
                case 8:
                    sc2 = new Scanner(System.in);
                    System.out.println("ingrese fecha, los productos posteriores a la misma se mostraran");
                    fechaenstring = sc2.nextLine();
                    System.out.println(fechaenstring + " fechaenstring es");
                    fechafecha = formatedate.parse(fechaenstring);
                    facturaFiltro = lista.stream()
                            .filter(elemento -> elemento.getFechaFactura().after(fechafecha)).collect(Collectors.toList());

                    for (Factura o : facturaFiltro) {
                        System.out.println("la factura es del producto " + o.getDescripcion() + " y su fecha es " + formatedate.format(o.getFechaFactura()));
                    }
                    break;
                case 9:
                    sc2 = new Scanner(System.in);
                    System.out.println("ingrese fecha, los productos anteriores a la misma se mostraran");
                    System.out.print("Fecha (Dia/Mes/año) ej : 12/10/2020.. :");
                    fechaenstring = sc2.nextLine();
                    System.out.println(fechaenstring + " fechaenstring es");
                    fechafecha = formatedate.parse(fechaenstring);
                    facturaFiltro = lista.stream()
                            .filter(elemento -> elemento.getFechaFactura().before(fechafecha)).collect(Collectors.toList());

                    for (Factura o : facturaFiltro) {
                        System.out.println("la factura es del producto " + o.getDescripcion() + " y su fecha es " + formatedate.format(o.getFechaFactura()));
                    }
                    break;
                case 10:
                    facturaFiltro = lista.stream()
                            .filter(elemento -> elemento.getCantidadProducto() < cantidad).collect(Collectors.toList());


                    break;
                case 11:
                    facturaFiltro = lista.stream()
                            .filter(elemento -> elemento.getCantidadProducto() > cantidad).collect(Collectors.toList());

                    break;
                case 12:
                    facturaFiltro = lista.stream()
                            .filter(elemento -> elemento.getCantidadProducto() == codigo).collect(Collectors.toList());

                    break;
                case 0:
                    break;
            }
        } while (opcion != 0);
        // System.out.println("FACTURA UNICA "+facturaFiltro.getImporte());




        /*
        Factura facturaFiltro=lista.stream()
                .filter(elemento->elemento.getImporte()>300)
                .findFirst()
                .get();
        System.out.println(facturaFiltro.getImporte());
        */
    }
}

