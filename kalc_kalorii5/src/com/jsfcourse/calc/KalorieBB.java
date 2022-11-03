package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KalorieBB {
	private String wiek;
	private String waga;
	private String wzrost;
	private String plec;
	private Double result;
	

	public String getWiek() {
		return wiek;
	}

	public void setWiek(String wiek) {
		this.wiek = wiek;
	}

	public String getWaga() {
		return waga;
	}

	public void setWaga(String waga) {
		this.waga = waga;
	}

	public String getWzrost() {
		return wzrost;
	}

	public void setWzrost(String wzrost) {
		this.wzrost = wzrost;
	}

	public String getPlec() {
		return plec;
	}

	public void setPlec(String plec) {
		this.plec = plec;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}



	@Inject
	FacesContext ctx;

	public boolean doTheMath() {
		try {

			double wiek = Double.parseDouble(this.wiek);
			double waga = Double.parseDouble(this.waga);
			double wzrost = Double.parseDouble(this.wzrost);
			String plec = this.plec;
			
			if (plec.equals("mezczyzna")) {
				result = (66.5 + (13.7 * waga) + (5 * wzrost) - (6.8 * wiek));
			} else if (plec.equals("kobieta")) {
				result = (655 + (9.6 * waga) + (1.85 * wzrost) - (4.7 * wiek));
			} else {
				result = null;
			}
			
			

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}

	// Put result in messages on AJAX call
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Twoje zapotrzebowanie wynosi: " + result, null));
		}
		return null;
	}

}
