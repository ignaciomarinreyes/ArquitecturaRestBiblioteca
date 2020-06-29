package biblioteca;

import biblioteca.AlquilerPK;
import biblioteca.Clientes;
import biblioteca.Libros;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-22T15:04:31")
@StaticMetamodel(Alquiler.class)
public class Alquiler_ { 

    public static volatile SingularAttribute<Alquiler, Libros> libros;
    public static volatile SingularAttribute<Alquiler, Date> retorno;
    public static volatile SingularAttribute<Alquiler, AlquilerPK> alquilerPK;
    public static volatile SingularAttribute<Alquiler, Clientes> clientes;

}