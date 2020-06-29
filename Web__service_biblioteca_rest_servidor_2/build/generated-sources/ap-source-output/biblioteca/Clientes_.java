package biblioteca;

import biblioteca.Alquiler;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-22T15:04:31")
@StaticMetamodel(Clientes.class)
public class Clientes_ { 

    public static volatile SingularAttribute<Clientes, Integer> rango;
    public static volatile CollectionAttribute<Clientes, Alquiler> alquilerCollection;
    public static volatile SingularAttribute<Clientes, String> correo;
    public static volatile SingularAttribute<Clientes, Boolean> membresia;
    public static volatile SingularAttribute<Clientes, String> contrasena;
    public static volatile SingularAttribute<Clientes, String> telefono;
    public static volatile SingularAttribute<Clientes, String> nombre;
    public static volatile SingularAttribute<Clientes, String> dni;

}