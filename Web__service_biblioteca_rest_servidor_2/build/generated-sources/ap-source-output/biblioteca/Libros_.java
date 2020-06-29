package biblioteca;

import biblioteca.Alquiler;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-22T15:04:31")
@StaticMetamodel(Libros.class)
public class Libros_ { 

    public static volatile SingularAttribute<Libros, String> editorial;
    public static volatile SingularAttribute<Libros, String> libID;
    public static volatile SingularAttribute<Libros, Date> ano;
    public static volatile CollectionAttribute<Libros, Alquiler> alquilerCollection;
    public static volatile SingularAttribute<Libros, Integer> popularidad;
    public static volatile SingularAttribute<Libros, Integer> cantidad;
    public static volatile SingularAttribute<Libros, String> nombre;
    public static volatile SingularAttribute<Libros, String> autor;

}