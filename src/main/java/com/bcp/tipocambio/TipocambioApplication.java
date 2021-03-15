package com.bcp.tipocambio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class TipocambioApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate template;

	public static void main(String[] args) {
		SpringApplication.run(TipocambioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		template.execute("DROP TABLE moneda IF EXISTS");
		template.execute("CREATE TABLE moneda (id INT AUTO_INCREMENT  PRIMARY KEY,descripcion VARCHAR(250) NOT NULL)");
		template.execute("INSERT INTO moneda (descripcion) VALUES ('Sol Peruano')");
		template.execute("INSERT INTO moneda (descripcion) VALUES ('Dolar Estadounidense')");
		template.execute("INSERT INTO moneda (descripcion) VALUES ('Peso Argentino')");
		template.execute("INSERT INTO moneda (descripcion) VALUES ('Peso Chileno')");

		template.execute("DROP TABLE tasa_cambio IF EXISTS");
		template.execute("CREATE TABLE tasa_cambio (id INT AUTO_INCREMENT  PRIMARY KEY,id_moneda_origen INT NOT NULL,id_moneda_destino INT NOT NULL,valor DOUBLE NOT NULL)");
		template.execute("INSERT INTO tasa_cambio (id_moneda_origen, id_moneda_destino, valor) VALUES (1,2,0.26945)");
		template.execute("INSERT INTO tasa_cambio (id_moneda_origen, id_moneda_destino, valor) VALUES (1,3,24.46317)");
		template.execute("INSERT INTO tasa_cambio (id_moneda_origen, id_moneda_destino, valor) VALUES (1,4,195.16934)");
	}
}
