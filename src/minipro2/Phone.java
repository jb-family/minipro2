package minipro2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Phone {
	
	private static List<Person> pList = new ArrayList<Person>();	
	
	public static void loadList() {	//데이터 저장
		try {
			Reader rd = new FileReader("./PhoneDB.txt");
			BufferedReader br = new BufferedReader(rd);
			
			while(true) {
				String read = br.readLine();
				
				if(read == null) {
					break;
				}
				String[] sArray = read.split(",");
				
				String name = sArray[0];
				String hp = sArray[1];
				String company = sArray[2];
				
				Person person = new Person();
				person.setName(name);
				person.setHp(hp);
				person.setCompany(company);
				
				pList.add(person);
			}
			
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveList() {
		try {
			Writer wt = new FileWriter("./PhoneDB.txt");
			BufferedWriter bw = new BufferedWriter(wt);
			
			for(int i = 0; i < pList.size(); i++) {
				
				bw.write(pList.get(i).getName() + "," + pList.get(i).getHp() + "," + pList.get(i).getCompany());
				bw.newLine();
				bw.flush();
			}
			
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
		public void printList() {
			printList("");
		}//메소드 오버로딩
	
	private void printList(String key) {
		for(int i = 0; i < pList.size(); i++) {
			if(pList.get(i).getName().contains(key)) {
				System.out.print(i+1 + ".  " + pList.get(i).getName() + "\t");
				System.out.print(pList.get(i).getHp() + "\t");
				System.out.print(pList.get(i).getCompany() + "\t");
				System.out.println("");
			}
			
		}
	}
	
	public void endList() {
		System.out.println("***************************************");
		System.out.println("*              감사합니다              *");
		System.out.println("***************************************");
	}
	
	
	public static void main(String[] args) {
		
		loadList();	//파일 읽어온 데이터 출력.
		
		Phone phone = new Phone();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("***************************************");
		System.out.println("*         전화번호 관리 프로그램         *");
		System.out.println("***************************************");
		
		boolean run = true;
				
		while(run) {
			System.out.println("1.리스트  2.등록  3.삭제  4.검색  5.종료");
			System.out.println("----------------------------------------");
			System.out.print(">메뉴번호 :");
			int num = sc.nextInt();
		
			switch(num) {
			
			case 1: System.out.println("<1.리스트>");
					phone.printList();
			break;
			
			case 2: System.out.println("<2.등록>");
					System.out.print(">이름 :");
					String name = sc.next();
					System.out.print(">휴대전화 :");
					String hp = sc.next();
					System.out.print(">회사전화 :");
					String company = sc.next();
					
					Person person = new Person();
					person.setName(name);
					person.setHp(hp);
					person.setCompany(company);
					
					pList.add(person);
					phone.saveList();
					System.out.println("[등록되었습니다.]");
			break;
			
			case 3: System.out.println("<3.삭제>");		
					System.out.print(">번호 :");
					int no = sc.nextInt();
					pList.remove(no -1);
					phone.saveList();
					System.out.println("[삭제되었습니다.]");
			break;
			
			case 4: System.out.println("<4.검색>");		
					System.out.print(">이름 :");
					String search = sc.next();
					phone.printList(search);
			break;	
			
			case 5: System.out.println("<5.종료>");
					run = false;
			break;
			
			default: System.out.println("다시 입력해주세요.");
			
			}//for문 끝
		}//while문 끝
		phone.endList();//프로그램 종료
		
		sc.close();//스캐너 종료
		
		
	}
	
	
	
	
}
