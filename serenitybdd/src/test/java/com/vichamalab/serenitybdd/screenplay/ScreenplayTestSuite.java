package com.vichamalab.serenitybdd.screenplay;

import static net.serenitybdd.screenplay.GivenWhenThen.givenThat;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.GivenWhenThen.then;
import static net.serenitybdd.screenplay.GivenWhenThen.when;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.vichamalab.serenitybdd.screenplay.question.Articulo;
import com.vichamalab.serenitybdd.screenplay.task.Buscar;
import com.vichamalab.serenitybdd.screenplay.task.Navegar;
import com.vichamalab.serenitybdd.screenplay.task.Seleccionar;

import io.cucumber.java.Before;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.annotations.CastMember;

@ExtendWith(SerenityJUnit5Extension.class)
public class ScreenplayTestSuite {
	//@Managed(driver="chrome")
	private WebDriver navegador;
	
	@CastMember(name = "UsuarioAnonimo")    
	Actor anonimo;	
	
    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); 
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

        navegador = new ChromeDriver(options);
    }
    
	@Test
	@Tag("screenplay")
	@DisplayName("Buscar en wiki de viaje usando ScreenPlay Pattern")
	public void buscarPalabrasClave() {
		anonimo.can(BrowseTheWeb.with(navegador));
		anonimo.has(Navegar.paginainicio());
		anonimo.attemptsTo(Buscar.porPalabraClave("arequipa"),Seleccionar.primerResultado());
		anonimo.should(
				seeThat("El titulo del articulo", Articulo.titulo(),equalTo("Arequipa"))
				);
	}
	
	@Test
	@Tag("screenplay")
	@DisplayName("Buscar en wiki de viaje usando ScreenPlay Pattern with BDD")
	public void buscarPalabrasClaveBDD() {	
		anonimo.can(BrowseTheWeb.with(navegador));
		givenThat(anonimo).wasAbleTo(Navegar.paginainicio());
		when(anonimo).attemptsTo(Buscar.porPalabraClave("arequipa"),Seleccionar.primerResultado());
		then(anonimo).should(
				seeThat("El titulo del articulo", Articulo.titulo(),equalTo("Arequipa"))
				);
	}	
}
