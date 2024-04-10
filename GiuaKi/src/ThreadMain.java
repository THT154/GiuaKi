import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ThreadMain extends Thread {
	private Student student;
	private int sumOfDigits;
	private Period age;

	public ThreadMain(Student student) {
		this.student = student;
	}

	public void Thread2() {

		LocalDate dob = student.getDateOfBirth();

		LocalDate now = LocalDate.now();
		age = Period.between(dob, now);

		System.out.println("-----------------------------------");
		System.out.println("Tính tuổi của " + student.getName());
		System.out.println("Years: " + age.getYears());
		System.out.println("Months: " + age.getMonths());
		System.out.println("Days: " + age.getDays());

		String encodedAge = encodeAge(age.getYears(), age.getMonths(), age.getDays());

		System.out.println("Tuổi sau khi mã hóa " + student.getName() + ": " + encodedAge);

	}

	public void Thread3() {
		LocalDate dob = student.getDateOfBirth();

		sumOfDigits = calculateSumOfDigits(dob.toString().replace("-", ""));

		boolean isPrime = isPrime(sumOfDigits);

		System.out.println("-----------------------------------");
		System.out.println("Tổng các chữ số trong ngày tháng năm sinh của " + student.getName() + ": " + sumOfDigits);
		if (isPrime == true) {
			System.out.println(sumOfDigits + " là một số nguyên tố");
		} else {
			System.out.println(sumOfDigits + " không phải là một số nguyên tố");

		}

	}

	

	public void writeStudentsToXML(List<Student> students, String filename) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();

			// Tạo phần tử gốc <students>
			Element rootElement = doc.createElement("students");
			doc.appendChild(rootElement);

			// Lặp qua từng sinh viên và ghi thông tin vào tệp XML
			for (Student student : students) {
				Element studentElement = doc.createElement("student");
				rootElement.appendChild(studentElement);

				Element yearElement = doc.createElement("Year");
				yearElement.appendChild(doc.createTextNode(String.valueOf(age.getYears())));
				studentElement.appendChild(yearElement);

				Element monthElement = doc.createElement("Months");
				monthElement.appendChild(doc.createTextNode(String.valueOf(age.getMonths())));
				studentElement.appendChild(monthElement);

				Element dayElement = doc.createElement("Days");
				dayElement.appendChild(doc.createTextNode(String.valueOf(age.getDays())));
				studentElement.appendChild(dayElement);

				ThreadMain thread3 = new ThreadMain(student);
				thread3.start();

				Element sumElement = doc.createElement("sum");
				sumElement.appendChild(doc.createTextNode(String.valueOf(sumOfDigits)));
				studentElement.appendChild(sumElement);

				Element isDigitElement = doc.createElement("isDigit");
				isDigitElement.appendChild(doc.createTextNode(String.valueOf(isPrime(sumOfDigits))));
				studentElement.appendChild(isDigitElement);
			}

			// Ghi dữ liệu vào tệp XML
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(filename);
			transformer.transform(source, result);

		} catch (ParserConfigurationException | TransformerException ex) {
			ex.printStackTrace();
		}
	}

	private int calculateSumOfDigits(String number) {
		int sum = 0;
		for (int i = 0; i < number.length(); i++) {
			sum += Character.getNumericValue(number.charAt(i));
		}
		return sum;
	}

	private boolean isPrime(int number) {
		if (number <= 1) {
			return false;
		}
		for (int i = 2; i <= Math.sqrt(number); i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	private String encodeAge(int years, int months, int days) {
		return String.valueOf(years) + String.valueOf(months) + String.valueOf(days);
	}

}
