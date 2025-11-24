package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.CarRental;
import entities.Vehicle;
import model.services.BrazilTaxServices;
import model.services.RentalServices;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do carro: ");
		String carModel = sc.nextLine();
		System.out.print("Retirada (dd/MM/yyyy hh:MM:ss): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);		
		System.out.print("Retorno (dd/MM/yyyy hh:MM:ss): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(),fmt);
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));	
		
		System.out.print("Entre com o preço por hora: ");
		Double priceHour = sc.nextDouble();
		System.out.print("Entre com o preço por dia: ");
		Double priceDay = sc.nextDouble();
		
		RentalServices rentalService = new RentalServices(priceHour, priceDay, new BrazilTaxServices());
		rentalService.processInvoice(cr);
		
		System.out.println("FATURA");
		System.out.println("Pagamento basico: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Imposto: " + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Pagamento total: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
		
		
		
		
		sc.close();
	}

}
