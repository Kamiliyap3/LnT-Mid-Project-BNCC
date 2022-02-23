import java.util.Scanner;
import java.util.Vector;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Vector<Karyawan> karyawan = new Vector<Karyawan>();
		//delsoon
		while(true) {
			System.out.println("LnT Mid Project");
			System.out.println("1. Insert data karyawan");
			System.out.println("2. View data karyawan");
			System.out.println("3. Update data karyawan");
			System.out.println("4. Delete data karyawan");
			System.out.println("5. Exit");
			System.out.print(">> ");
			int menu = sc.nextInt(); sc.nextLine();
			switch (menu) {
			case 1: {
				addKaryawan(karyawan);
				break;
			}
			case 2:{
				viewKaryawan(karyawan);
				break;
			}
			case 3:{
				UpdateKaryawan(karyawan);
				break;
			}
			case 4:{
				DeleteKaryawan(karyawan);
				break;
			}
			case 5:{
				System.exit(0);
				break;
			}
			default:
				break;
			}
		}
		
	}
	
	public static void DeleteKaryawan(Vector<Karyawan> karyawan) {
		viewKaryawan(karyawan);
		if(karyawan.size()>0) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Input nomor urutan karyawan yang ingin dihapus : ");
			int answer = Integer.parseInt(sc.nextLine());
			if(answer>0&&answer<=karyawan.size()) {
				System.out.println("Karyawan dengan kode "+karyawan.get(answer-1).GetId()+" berhasil dihapus.");
				karyawan.remove(answer-1);
				System.out.println("ENTER to return");
				sc.nextLine();
			}
		}
	}
	
	public static boolean ValidatingId(Vector<Karyawan> karyawan,String kode) {
		kode=kode.toUpperCase();
		if(kode.length()<=6) {
			if(Character.isAlphabetic(kode.charAt(0))) {
				if(Character.isAlphabetic(kode.charAt(1))) {
					if(kode.charAt(2)=='-') {
						for(int i=3;i<6;i++) {
							if(!Character.isDigit(kode.charAt(i))) {
								System.out.println("Format id salah!");
								return false;
							}
						}
					}
					else {
						System.out.println("Format id salah!");
						return false;
					}
				}
			}
			for(Karyawan k:karyawan) {
				if(k.GetId().equals(kode)) {
					System.out.println("Id Sudah digunakan!");
					return false;
				}
			}
			return true;
		}
		else {
			System.out.println("Kode karyawan terlalu panjang!");
			return false;
		}
		
	}
	
	public static void UpdateKaryawan(Vector<Karyawan> karyawan) {
		viewKaryawan(karyawan);
		
		if(karyawan.size()>0) {
			Scanner sc = new Scanner(System.in);
			System.out.print("\nMasukan nomor urutan karyawan yang ingin diupdate: ");
			int answer = Integer.parseInt(sc.nextLine());
			if(answer>0&&answer<=karyawan.size()) {
				String kode;
				while(true) {
					System.out.print("Input Kode karyawan : ");
					kode = sc.nextLine();
					if(kode.equals("0")) {
						kode=karyawan.get(answer-1).GetId();
						break;
					}
					else if(!kode.equals("0")) {
						if(ValidatingId(karyawan, kode)) {
							break;
						}
					}
				}
				
				
				while(true) {
					System.out.print("Input nama karyawan [>=3]: ");
					String nama = sc.nextLine();
					if(!nama.equals("0")) {
						if(nama.length()>=3) {
							karyawan.get(answer-1).SetNama(nama);
							break;
						}
					}
					else if(nama.equals("0")) {
						break;
					}
				}
				while(true) {
					System.out.print("Input jenis kelamin [Laki-laki | Perempuan]: ");
					String sex = sc.nextLine();
					if(sex.equals("0")) {
						break;
					}
					else if(!sex.equals("0")) {
						if(sex.equals("Laki-laki")||sex.equals("Perempuan")) {
							karyawan.get(answer-1).SetSex(sex);
							break;
						}
					}
				}
				while(true) {
					System.out.print("Input jabatan [Manager| Supervisor | Admin]: ");
					String jabatan = sc.nextLine();
					if(jabatan.equals("0")) {
						break;
					}
					else if(!jabatan.equals("0")) {
						if(jabatan.equals("Manager")||jabatan.equals("Supervisor")||jabatan.equals("Admin")) {
							karyawan.get(answer-1).SetJabatan(jabatan);
							break;
						}
					}
				}
				
				while(true) {
					System.out.print("Input Gaji Karyawan: ");
					Double gaji = Double.parseDouble(sc.nextLine());
					if(gaji==0) {
						System.out.println("Berhasil mengupdate karyawan dengan id "+karyawan.get(answer-1).GetId());
						karyawan.get(answer-1).SetId(kode);
						System.out.println("ENTER to return");
						sc.nextLine();
						break;
					}
					else if(gaji!=0&&gaji>0) {
						karyawan.get(answer-1).SetGaji(gaji);
						System.out.println("Berhasil mengupdate karyawan dengan id "+karyawan.get(answer-1).GetId());
						karyawan.get(answer-1).SetId(kode);
						System.out.println("ENTER to return");
						sc.nextLine();
						break;
					}
				}
			}
		}
	}
	
	public static String generateId(Vector<Karyawan> karyawan) {
		Random ran= new Random();
		boolean status=false;
		while(true) {
			String temp = (String)(Character.toString((char)(ran.nextInt(26)+'a'))
					+ Character.toString((char)(ran.nextInt(26)+'a'))+'-'
					+ Integer.toString(ran.nextInt(10)) + Integer.toString(ran.nextInt(10))
					+ Integer.toString(ran.nextInt(10))).toUpperCase();
			
			for(Karyawan k:karyawan) {
				if(temp.equals(k.GetId())) {
					status=true;
					break;
				}
			}
			if(status) {
				continue;
			}
			else return temp;
		}
		
		
	}
	
	public static void promoteKaryawan(Vector<Karyawan> karyawan, String jabatan) {
		int jumlahKaryawan =0;
		for(Karyawan k:karyawan) {
			if(jabatan.equals(k.GetJabatan())) {
				jumlahKaryawan++;
			}
		}
		if(jumlahKaryawan%3==0) {
			int tempCount=0;
			Vector<String> ids = new Vector<>();
			for(Karyawan k:karyawan) {
				if(k.GetJabatan().equals(jabatan)) {
					Double gajiTemp = k.GetGaji();
					if(jabatan.equals("Manager")) {
						k.SetGaji(gajiTemp*10/100+gajiTemp);
					}
					else if(jabatan.equals("Supervisor")) {
						k.SetGaji(gajiTemp*7.5/100+gajiTemp);
					}
					else if(jabatan.equals("Admin")) {
						k.SetGaji(gajiTemp*5/100+gajiTemp);
					}
					tempCount++;
					ids.add(k.GetId());
					if(tempCount==jumlahKaryawan) {
						if(jabatan.equals("Manager")) {
							System.out.print("Bonus sebesar 10% berhasil ditambahkan kepada karyawan dengan id\n");
							for(String t:ids) {
								System.out.print(t+" ");
							}
						}
						else if(jabatan.equals("Supervisor")) {
							System.out.print("Bonus sebesar 7.5% berhasil ditambahkan kepada karyawan dengan id\n");
							for(String t:ids) {
								System.out.print(t+" ");
							}
						}
						else if(jabatan.equals("Admin")) {
							System.out.print("Bonus sebesar 5% berhasil ditambahkan kepada karyawan dengan id\n");
							int a=0;
							for(String t:ids) {
								System.out.print(t);
								a++;
								if(a<ids.size()){
									System.out.print(", ");
								}
							}
						}
						break;
					}
				}
			}
		}
	}
	
	public static void viewKaryawan(Vector<Karyawan> karyawan) {
		
		if(karyawan.size()>0) {
			System.out.println("\n\n");
			//Vector<Karyawan> tempKaryawan = karyawan;
			for (int i = 0; i < karyawan.size() - 1; i++) {
	            for (int j = 0; j < karyawan.size() - i - 1; j++) {
	                if (karyawan.get(j).GetName().compareTo(karyawan.get(j+1).GetName()) > 0) {
	                    Karyawan temp= karyawan.get(j);
	                    karyawan.set(j, karyawan.get(j+1));
	                    karyawan.set(j+1, temp);
	                 }
	            }
	        }
			//63
			for(int i=0;i<114;i++) {
				System.out.print("=");
			}
			System.out.printf("\n| %-5s | %-15s | %-35s | %-15s | %-10s | %-15s |%n", "No","Kode Karyawan","Nama Karyawan","Jenis Kelamin","Jabatan","Gaji Karyawan");
			for(int i=0;i<114;i++) {
				System.out.print("=");
			}
			for(int i=0;i<karyawan.size();i++) {
				System.out.printf("\n| %5s | %15s | %35s | %15s | %10s | %15s |%n", 
						Integer.toString(i+1),karyawan.get(i).GetId(),karyawan.get(i).GetName(),karyawan.get(i).GetSex(),
						karyawan.get(i).GetJabatan(), Double.toString(karyawan.get(i).GetGaji()));
			}
			for(int i=0;i<114;i++) {
				System.out.print("=");
			}
			System.out.println("\n");
		}
		else {
			System.out.println("\n\n========================");
			System.out.println("\tNo Data");
			System.out.println("========================\n\n");
		}
		
	}
	
	public static void addKaryawan(Vector<Karyawan> karyawan) {
		
		String nama, id,jabatan,sex;
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.print("Input nama karyawan [>=3]: " );
			nama=sc.nextLine();
			if(nama.length()>=3) {
				break;
			}
		}
		
		while(true) {
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case sensitive): ");
			sex =sc.nextLine();
			if(sex.equals("Laki-laki")||sex.equals("Perempuan")) {
				break;
			}
		}
		while(true) {
			System.out.print("Input jabatan[Manager | Supervisor | Admin] (Case sensitive): ");
			jabatan = sc.nextLine();
			if(jabatan.equals("Manager")||jabatan.equals("Supervisor")||jabatan.equals("Admin")) {
				break;
			}
		}
		
		//generating id and checking
		id = generateId(karyawan);
		System.out.println("Berhasil menambahkan karyawan dengan id "+id);
		promoteKaryawan(karyawan, jabatan);
		//adding karyawan to vector
		karyawan.add(new Karyawan(nama, jabatan, id, sex));
		System.out.println("\nENTER to return\n\n\n");
		sc.nextLine();
	}
}

class Karyawan{
	private String Sex;
	private Double Gaji;
	private String Id;
	private String Name;
	private String Jabatan;
	
	public Karyawan(String nama, String jabatan,String id, String sex) {
		this.Name=nama;
		this.Jabatan=jabatan;
		if(jabatan.equals("Manager")) {
			this.Gaji=8000000d;
		}
		else if(jabatan.equals("Supervisor")) {
			this.Gaji=6000000d;
		}
		else if(jabatan.equals("Admin")) {
			this.Gaji=4000000d;
		}
		this.Id=id;
		this.Sex=sex;
	}
	
	public void SetId(String kode) {
		this.Id=kode;
		
	}

	public void SetJabatan(String jabatan2) {
		this.Jabatan=jabatan2;
	}

	public void SetSex(String sex2) {
		this.Sex=sex2;
	}

	public void SetNama(String nama) {
		this.Name=nama;
		
	}

	public void SetGaji(Double g) {
		this.Gaji=g;
	}
	
	public String GetSex() {
		return this.Sex;
	}
	
	public Double GetGaji() {
		return this.Gaji;
	}
	
	public String GetJabatan() {
		return this.Jabatan;
	}
	
	public String GetName() {
		return this.Name;
	}
	
	public String GetId() {
		return this.Id;
	}
	
}