use ws_a10;

DROP TABLE IF EXISTS alquiler;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS libros;
DROP TABLE IF EXISTS administrador;
DROP TABLE IF EXISTS pedido;

CREATE TABLE clientes(
	dni VARCHAR(9) PRIMARY KEY NOT NULL,
	nombre VARCHAR(60) NOT NULL,
	correo VARCHAR(255) NOT NULL,
	telefono VARCHAR(16),
	membresia boolean NOT NULL DEFAULT False,
	rango int NOT NULL DEFAULT 0,
	contrasena VARCHAR(255) NOT NULL
);

CREATE TABLE libros(
	libID int PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(255) NOT NULL,
	ano date,
	autor VARCHAR(60) NOT NULL,
	editorial varchar(60) NOT NULL,
	cantidad int NOT NULL DEFAULT 20,
	popularidad int NOT NULL DEFAULT 0
);

CREATE TABLE alquiler(
	cliDNI varchar(9) NOT NULL,
	boID int NOT NULL,
	retorno date NOT NULL,
    PRIMARY KEY(cliDNI, boID),
	FOREIGN KEY (cliDNI) REFERENCES clientes(dni) ON DELETE CASCADE,
	FOREIGN KEY (boID) REFERENCES libros(libID) ON DELETE CASCADE 
);

CREATE TABLE administrador(
	id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	usuario varchar(60) NOT NULL,
	contrasena varchar(255) NOT NULL	
);

CREATE TABLE pedido(
	boID int PRIMARY KEY NOT NULL,
	cantidad int NOT NULL,
	entregado boolean NOT NULL DEFAULT False
);

DELIMITER //
CREATE TRIGGER pedirLibro AFTER UPDATE ON libros
FOR EACH ROW
BEGIN
IF NEW.cantidad < 5 THEN
INSERT INTO pedido(boID, cantidad, entregado) VALUES(2, 20, False);
END IF;
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER cantidadNueva AFTER UPDATE ON pedido
FOR EACH ROW
BEGIN
IF (NEW.entregado) THEN
UPDATE libros SET libros.cantidad = libros.cantidad + OLD.cantidad WHERE libros.libID = NEW.boID ;
END IF;
END;
//

INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Introducción a la psicología","2005-01-01","Charles G. Morris","Pearson Educación");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Actividades matemáticas con niños de 0 a 6 años","1992-10-05","M. Cristina Lahora","Narcea Ediciones");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("La Puesta a tierra de instalaciones eléctricas y el R.A.T.","1990-01-01","Rogelio García Márquez","Marcombo");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Cómo educar a nuestros adolescentes","2006-01-01","Maite Vallet","WK Educación");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Cómo enseñar a leer y escribir","2008-06-30","Antonio Barbosa Heldt","Editorial Pax México");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Introducción a la psicología comunitaria","2004-01-01","Gonzalo Musitu Ochoa","Editorial UOC");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Introducción a la historia de la arquitectura","2005-01-01","José Ramón Alonso Pereira","Reverte");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("El aprendizaje estratégico en la eduación a distancia","2003-01-01","Cristina Del Mastro","Fondo Editorial PUCP");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("De aprendices a maestros","2002-12-30","Andrea Alliaud","Papers Editores");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Métodos de Bézier y B-splines","2005-01-01","Hartmut Prautzsch","KIT Scientific Publishing");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Código B","2004-01-01","David Zurdo","EDAF");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Hepatitis B","2007-01-01","Joaquín Balanzó Tintoré","MARGE BOOKS");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Reexpresión de Estado Financiero B-10","2004-01-01","Jaime Domínguez Orozco","Ediciones Fiscales ISEF");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Test de examen de conducir DGT permiso B - Turismos","2015-03-22","Adanat Seguridad Vial","Adanat");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Aprendiendo C en 24 Horas","2001-01-01","Tony Zhang","Pearson Educación");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Introducción a la programación en C","2010-06-01","Marco A. Peña Basurto","Univ. Politèc. de Catalunya");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Curso práctico de programación en C y C++","2001-01-01","Jorge Badenas Carpio","Universitat Jaume I");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Manual de programacion lenguaje c++","2005-04-21","S.l. Didact","MAD-Eduforma");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Cómo programar en C/C+","1995-01-01","Harvey M. Deitel","Pearson Educación");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("El Lenguaje de Progra C","1991-01-01","Brian W. Kernighan","Pearson Educación");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Hepatitis C","2008-11-01","Carlos Guarner Aguilar","MARGE BOOKS");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Cálculo","2006-01-01","George Brinton Thomas","Pearson Educación");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Pràctiques d'ecologia II","2005-01-01","Margarita Menéndez López","Edicions Universitat Barcelona");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Eugenio d'Ors, anécdota y categoría","2004-01-01","Antonio Lago Carballo","Marcial Pons Historia");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Invertir en I+D","2006-01-01","Josep M. Surís","Univ. Autònoma de Barcelona");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Estudi de les proteïno-quinases independents d'AMP cíclic de fetge de rata","1983-01-01","Àngels Mor","Institut d'Estudis Catalans");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("IV Reunió d’Arqueologia Cristiana Hispànica","1995-01-01","Josep M. Gurt","Institut d'Estudis Catalans");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Gos d'Atura","2005-01-01","Xavier Andreu i Bartrolí","Editorial HISPANO EUROPEA");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("El género biográfico en la obra de Eugenio d'Ors","1988-01-01","Ada Suárez","Anthropos Editorial");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Principios de anatomía fisiología e higiene","1995-01-01","Gilberto Gutiérrez Cirlos","Editorial Limusa");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Sistemas De Calidad E Inocuidad De Los Alimentos","2002-09-30","Fao","Food & Agriculture Org.");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Psicogénesis e historia de la ciencia","1982-01-01","Jean Piaget","Siglo XXI");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Fantasía e imaginación, su poder en la enseñanza primaria","1999-01-01","Kieran Egan","Ediciones Morata");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Análisis químico e instrumental moderno","1983-01-01","Harold Frederic Walton","Reverte");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Literatura, historia e historia de la literatura","1993-01-01","Roberto Calvo Sanz","Edition Reichenberger");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("E-actividades","2004-01-01","Gilly Salmon","Editorial UOC");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("La filosofía del derecho de K. Ch. F. Krause","2000-01-01","Francisco Querol Fernández","Univ Pontifica Comillas");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Cine y literatura en F. Scott Fitzgerald","2002-01-01","Patricia Fra López","Univ Santiago de Compostela");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Estudios de lingüística y filología hispánicas en honor de José G. Moreno de Alba","2003-01-01","Ascensión H. de León-Portilla","UNAM");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("G/a. Toxicología","1994-01-01","APQUA.","Reverte");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("El pensamiento krausista de G. Tiberghien","2004-01-01","Antolín C Sánchez Cuervo","Univ Pontifica Comillas");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("La filosofía de la historia de Johann G. Herder","2005-01-01","Francisco J. Contreras Peláez","Universidad de Sevilla");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Antología de Vinton G. Cerf","2010-02-01","Vinton G. Cerf","Universidad de Zaragoza");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("G'amala","2012-01-19","Hector Martinez","Xlibris Corporation");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("El Zippo de George G. Blaisdell","2002-04-01","Tobias Kuhn","Springer Science & Business Media");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Amigo se escribe con H","2003-01-01","María Fernanda Heredia","Editorial Norma");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("La psicología genético-dialéctica de H. Wallon y sus implicaciones educativas","1993-01-01","Raimundo Olano Rey","Universidad de Oviedo");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("El pensamiento antropológico de Lewis H. Morgan","1998-01-01","María Valdés Gázquez","Servei de Publicacions de la Universitat Autònoma de Barcelona");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Itinerari de Jaume I el Conqueridor","2004-01-01","Maria Teresa Ferrer i Mallol","Institut d'Estudis Catalans");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Dona i literatura","1997-01-01","Ferran Carbó","Universitat de València");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Narrativa i història","2002-01-01","Assumpta Bernal","Universitat de València");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Pràctiques d'ecologia I","2005-01-01","Josep Anton Morguí i Castelló","Edicions Universitat Barcelona");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Estudis sobre els drets i institucions locals en la Catalunya medieval","1985-01-01","José María Font Rius","Edicions Universitat Barcelona");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Epistolari","1922-01-01","Milà i Fontanals, Manuel","Institut d'Estudis Catalans");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Castells, torres i fortificacions en la Ribera del Xúquer","2002-01-01","Antoni Furió","Universitat de València");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Què no hem de deixar de tastar a Vilanova i la Geltrú","2006-01-01","Ramon Francàs Martorell","Cossetània Edicions");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Pedro J. Ramírez, al desnudo","2009-01-01","José Díaz Herrera","Ediciones AKAL");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("J. Solana","2003-01-01","Ricardo López Serrano","Ed. Universidad de Cantabria");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Pedro J.","2008-01-01","Eduardo Martínez Rico","Plaza & Janes Editories Sa");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("J de juegos","2007-01-01","Matilde Magdalena","Intermón Oxfam Editorial");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Manual de redaccíon k'ichee'","1994-01-01","Candelaria Dominga López Ixcoy","Cholsamaj Fundacion");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Evolución del reino K'iche'","2001-01-01","Robert M. Carmack","Cholsamaj Fundacion");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("K'ichee' choltziij","2001-01-01","Pedro Florentino Ajpacajá Túm","Cholsamaj Fundacion");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("K Sigma","2006-01-01","Gonzalo Gómez Dacal","WK Educación");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("K.","2014-12-20","Roberto Calasso","Anagrama");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Brique et ses dérivés à l'époque romaine","1999-01-01","Manuel Bendala Galán","Casa de Velázquez");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("L'AGENDA PER A L'EMBARAS","2002-10-15","MARGUERITE SMOLEN","Editorial AMAT");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("La représentation du favori dans l'Espagne de Philippe III et de Philippe IV","2010-01-01","Hélène Tropé","Presses Sorbonne Nouvelle");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Formes brèves de l'expression culturelle en Amérique latine de 1850 à nos jours: Poésie, théâtre, chanson, chronique, essai","1997-01-01","Centre de recherches interuniversitaire sur les champs culturels en Amérique latine","Presses Sorbonne Nouvelle");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("L'arquitectura civil del segle XVII a Barcelona","1996-01-01","Antònia Maria Perelló Ferrer","L'Abadia de Montserrat");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Noves reflexions a l'entorn de l'educació estètica","1998-01-01","Eulàlia Collelldemont","Edicions Universitat Barcelona");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("L'Heptaméron","1988-01-01","José Antonio González Alcaraz","EDITUM");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Conceyu de L.lena. Parroquia de Congostinas","1993-01-01","Marta E. Santamarta Santos","Academia Llingua Asturiana");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("M'hijo el doctor","2004-01-01","César Álvarez Pérez","Ediciones Colihue SRL");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Epistolari","1922-01-01","Milà i Fontanals, Manuel","Institut d'Estudis Catalans");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Misiones y Leandro N. Alem","2005-01-01","Alba Isabela Durán","Editorial Dunken");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Didáctica del número real","1990-01-01","Antonio González Carlomán","Universidad de Oviedo");
INSERT INTO libros(nombre, ano, autor, editorial) VALUES ("Investigaci?n agr?cola en el territorio de Misiones","1904-01-01","P.J. Yssouribehere","Рипол Классик");

DROP TABLE IF EXISTS libreria;

CREATE TABLE libreria(
	libID int PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(255) NOT NULL,
	ano date,
	autor VARCHAR(60) NOT NULL,
	editorial varchar(60) NOT NULL,
	cantidad int NOT NULL DEFAULT 20
);

INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Introducción a la psicología","2005-01-01","Charles G. Morris","Pearson Educación");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Actividades matemáticas con niños de 0 a 6 años","1992-10-05","M. Cristina Lahora","Narcea Ediciones");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("La Puesta a tierra de instalaciones eléctricas y el R.A.T.","1990-01-01","Rogelio García Márquez","Marcombo");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Cómo educar a nuestros adolescentes","2006-01-01","Maite Vallet","WK Educación");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Cómo enseñar a leer y escribir","2008-06-30","Antonio Barbosa Heldt","Editorial Pax México");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Introducción a la psicología comunitaria","2004-01-01","Gonzalo Musitu Ochoa","Editorial UOC");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Introducción a la historia de la arquitectura","2005-01-01","José Ramón Alonso Pereira","Reverte");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("El aprendizaje estratégico en la eduación a distancia","2003-01-01","Cristina Del Mastro","Fondo Editorial PUCP");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("De aprendices a maestros","2002-12-30","Andrea Alliaud","Papers Editores");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Métodos de Bézier y B-splines","2005-01-01","Hartmut Prautzsch","KIT Scientific Publishing");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Código B","2004-01-01","David Zurdo","EDAF");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Hepatitis B","2007-01-01","Joaquín Balanzó Tintoré","MARGE BOOKS");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Reexpresión de Estado Financiero B-10","2004-01-01","Jaime Domínguez Orozco","Ediciones Fiscales ISEF");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Test de examen de conducir DGT permiso B - Turismos","2015-03-22","Adanat Seguridad Vial","Adanat");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Aprendiendo C en 24 Horas","2001-01-01","Tony Zhang","Pearson Educación");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Introducción a la programación en C","2010-06-01","Marco A. Peña Basurto","Univ. Politèc. de Catalunya");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Curso práctico de programación en C y C++","2001-01-01","Jorge Badenas Carpio","Universitat Jaume I");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Manual de programacion lenguaje c++","2005-04-21","S.l. Didact","MAD-Eduforma");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Cómo programar en C/C+","1995-01-01","Harvey M. Deitel","Pearson Educación");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("El Lenguaje de Progra C","1991-01-01","Brian W. Kernighan","Pearson Educación");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Hepatitis C","2008-11-01","Carlos Guarner Aguilar","MARGE BOOKS");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Cálculo","2006-01-01","George Brinton Thomas","Pearson Educación");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Pràctiques d'ecologia II","2005-01-01","Margarita Menéndez López","Edicions Universitat Barcelona");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Eugenio d'Ors, anécdota y categoría","2004-01-01","Antonio Lago Carballo","Marcial Pons Historia");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Invertir en I+D","2006-01-01","Josep M. Surís","Univ. Autònoma de Barcelona");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Estudi de les proteïno-quinases independents d'AMP cíclic de fetge de rata","1983-01-01","Àngels Mor","Institut d'Estudis Catalans");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("IV Reunió d’Arqueologia Cristiana Hispànica","1995-01-01","Josep M. Gurt","Institut d'Estudis Catalans");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Gos d'Atura","2005-01-01","Xavier Andreu i Bartrolí","Editorial HISPANO EUROPEA");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("El género biográfico en la obra de Eugenio d'Ors","1988-01-01","Ada Suárez","Anthropos Editorial");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Principios de anatomía fisiología e higiene","1995-01-01","Gilberto Gutiérrez Cirlos","Editorial Limusa");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Sistemas De Calidad E Inocuidad De Los Alimentos","2002-09-30","Fao","Food & Agriculture Org.");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Psicogénesis e historia de la ciencia","1982-01-01","Jean Piaget","Siglo XXI");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Fantasía e imaginación, su poder en la enseñanza primaria","1999-01-01","Kieran Egan","Ediciones Morata");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Análisis químico e instrumental moderno","1983-01-01","Harold Frederic Walton","Reverte");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Literatura, historia e historia de la literatura","1993-01-01","Roberto Calvo Sanz","Edition Reichenberger");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("E-actividades","2004-01-01","Gilly Salmon","Editorial UOC");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("La filosofía del derecho de K. Ch. F. Krause","2000-01-01","Francisco Querol Fernández","Univ Pontifica Comillas");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Cine y literatura en F. Scott Fitzgerald","2002-01-01","Patricia Fra López","Univ Santiago de Compostela");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Estudios de lingüística y filología hispánicas en honor de José G. Moreno de Alba","2003-01-01","Ascensión H. de León-Portilla","UNAM");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("G/a. Toxicología","1994-01-01","APQUA.","Reverte");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("El pensamiento krausista de G. Tiberghien","2004-01-01","Antolín C Sánchez Cuervo","Univ Pontifica Comillas");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("La filosofía de la historia de Johann G. Herder","2005-01-01","Francisco J. Contreras Peláez","Universidad de Sevilla");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Antología de Vinton G. Cerf","2010-02-01","Vinton G. Cerf","Universidad de Zaragoza");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("G'amala","2012-01-19","Hector Martinez","Xlibris Corporation");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("El Zippo de George G. Blaisdell","2002-04-01","Tobias Kuhn","Springer Science & Business Media");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Amigo se escribe con H","2003-01-01","María Fernanda Heredia","Editorial Norma");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("La psicología genético-dialéctica de H. Wallon y sus implicaciones educativas","1993-01-01","Raimundo Olano Rey","Universidad de Oviedo");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("El pensamiento antropológico de Lewis H. Morgan","1998-01-01","María Valdés Gázquez","Servei de Publicacions de la Universitat Autònoma de Barcelona");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Itinerari de Jaume I el Conqueridor","2004-01-01","Maria Teresa Ferrer i Mallol","Institut d'Estudis Catalans");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Dona i literatura","1997-01-01","Ferran Carbó","Universitat de València");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Narrativa i història","2002-01-01","Assumpta Bernal","Universitat de València");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Pràctiques d'ecologia I","2005-01-01","Josep Anton Morguí i Castelló","Edicions Universitat Barcelona");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Estudis sobre els drets i institucions locals en la Catalunya medieval","1985-01-01","José María Font Rius","Edicions Universitat Barcelona");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Epistolari","1922-01-01","Milà i Fontanals, Manuel","Institut d'Estudis Catalans");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Castells, torres i fortificacions en la Ribera del Xúquer","2002-01-01","Antoni Furió","Universitat de València");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Què no hem de deixar de tastar a Vilanova i la Geltrú","2006-01-01","Ramon Francàs Martorell","Cossetània Edicions");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Pedro J. Ramírez, al desnudo","2009-01-01","José Díaz Herrera","Ediciones AKAL");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("J. Solana","2003-01-01","Ricardo López Serrano","Ed. Universidad de Cantabria");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Pedro J.","2008-01-01","Eduardo Martínez Rico","Plaza & Janes Editories Sa");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("J de juegos","2007-01-01","Matilde Magdalena","Intermón Oxfam Editorial");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Manual de redaccíon k'ichee'","1994-01-01","Candelaria Dominga López Ixcoy","Cholsamaj Fundacion");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Evolución del reino K'iche'","2001-01-01","Robert M. Carmack","Cholsamaj Fundacion");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("K'ichee' choltziij","2001-01-01","Pedro Florentino Ajpacajá Túm","Cholsamaj Fundacion");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("K Sigma","2006-01-01","Gonzalo Gómez Dacal","WK Educación");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("K.","2014-12-20","Roberto Calasso","Anagrama");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Brique et ses dérivés à l'époque romaine","1999-01-01","Manuel Bendala Galán","Casa de Velázquez");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("L'AGENDA PER A L'EMBARAS","2002-10-15","MARGUERITE SMOLEN","Editorial AMAT");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("La représentation du favori dans l'Espagne de Philippe III et de Philippe IV","2010-01-01","Hélène Tropé","Presses Sorbonne Nouvelle");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Formes brèves de l'expression culturelle en Amérique latine de 1850 à nos jours: Poésie, théâtre, chanson, chronique, essai","1997-01-01","Centre de recherches interuniversitaire sur les champs culturels en Amérique latine","Presses Sorbonne Nouvelle");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("L'arquitectura civil del segle XVII a Barcelona","1996-01-01","Antònia Maria Perelló Ferrer","L'Abadia de Montserrat");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Noves reflexions a l'entorn de l'educació estètica","1998-01-01","Eulàlia Collelldemont","Edicions Universitat Barcelona");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("L'Heptaméron","1988-01-01","José Antonio González Alcaraz","EDITUM");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Conceyu de L.lena. Parroquia de Congostinas","1993-01-01","Marta E. Santamarta Santos","Academia Llingua Asturiana");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("M'hijo el doctor","2004-01-01","César Álvarez Pérez","Ediciones Colihue SRL");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Epistolari","1922-01-01","Milà i Fontanals, Manuel","Institut d'Estudis Catalans");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Misiones y Leandro N. Alem","2005-01-01","Alba Isabela Durán","Editorial Dunken");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Didáctica del número real","1990-01-01","Antonio González Carlomán","Universidad de Oviedo");
INSERT INTO libreria(nombre, ano, autor, editorial) VALUES ("Investigaci?n agr?cola en el territorio de Misiones","1904-01-01","P.J. Yssouribehere","Рипол Классик");