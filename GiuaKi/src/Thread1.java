import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Thread1 {
	public static void main(String[] args) {
		// Tạo sinh viên
		LocalDate dob = LocalDate.of(2005, 4, 15);
//		Student student = new Student("1", "Trần Hữu Thắng", "Quảng Bình", dob);
		List<Student> danhsach = new ArrayList<Student>();

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse("GiuaKi/src/Student.xml");
			Element element = doc.getDocumentElement();
			
			NodeList ID = element.getElementsByTagName("ID");
			NodeList name = element.getElementsByTagName("name");
			NodeList address = element.getElementsByTagName("address");
			NodeList age = element.getElementsByTagName("dateOfBirth");
			for (int i = 0; i < name.getLength(); i++) {
				Student student = new Student();
				student.setID(ID.item(i).getTextContent());
				student.setName(name.item(i).getTextContent());
				student.setAddress(address.item(i).getTextContent());
			    String ageContent = age.item(i).getTextContent();
			    LocalDate dateOfBirth = LocalDate.parse(ageContent);
				student.setDateOfBirth(dateOfBirth);
				danhsach.add(student);

			}
			
			

		} catch (ParserConfigurationException | SAXException | IOException ex) {
			ex.printStackTrace();
		}

		for (Student student : danhsach) {
			ThreadMain thread = new ThreadMain(student);
			thread.Thread2();

			thread.Thread3();
			
			thread.writeStudentsToXML(danhsach, "GiuaKi/src/kq.xml");
		}
		
		

	}
}
