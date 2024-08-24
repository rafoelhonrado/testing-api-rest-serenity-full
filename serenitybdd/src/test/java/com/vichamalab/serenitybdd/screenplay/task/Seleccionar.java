package com.vichamalab.serenitybdd.screenplay.task;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Seleccionar implements Task {

    private static final Target RESULT_LINKS = Target.the("Search result links")
            .located(By.cssSelector(".mw-search-result-heading > a"));

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<WebElementFacade> resultLinks = RESULT_LINKS.resolveAllFor(actor);
        if (resultLinks.size() > 0) {
            WebElementFacade firstResult = resultLinks.get(0);
            actor.attemptsTo(
                Click.on(firstResult)
            );
        } else {
            throw new RuntimeException("No search result links found");
        }
    }

    public static Seleccionar primerResultado() {
        return instrumented(Seleccionar.class);
    }
}