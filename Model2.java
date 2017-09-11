import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Model2 {
	
	List<Memo> list;
	
	private static final String ROOT = "D:\\androidSchool4\\workspace\\file";
	private static final String FILE_NAME = "text.txt";
	private static final String INDEX_COUNT = "index.txt";
	private static final String SEP = "::";
	private int count = 0;
	
	File tempFile = new File(ROOT+"\\"+FILE_NAME);
	File indexFile = new File(ROOT+"\\"+INDEX_COUNT);
	
	// �߰�
	public void add(Memo memo) {
		
		File dir = new File(ROOT);
		
		// ���� ���� ���� ���丮�� �ִ��� �˻��ϰ� ������ ���丮�� �����
		if(!dir.exists()) {
			dir.mkdir();
		}
		
		// tempFile�� ������ ���� ����
		if(!tempFile.exists()) {
			try {
				tempFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		setCount();
		addCount();
		
		// byte�� ����� ���� String ��ȯ
		String oneLine = count+SEP+memo.title+SEP+memo.content+SEP+memo.date+"\n";
		
		// ���� ���Ͽ� oneLine �� �־��ֱ�
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile, true)));
			bw.append(oneLine);
			bw.flush();	// 
			bw.close();
		} catch(Exception e) {
			
		}	
	}
	
	// ��ü ��ȸ
	public List<Memo> read(){
		if(list != null)
		list.clear();
		list = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(tempFile)));
			String tempLine = "";
			while((tempLine = br.readLine()) != null) {
				Memo memo = new Memo();
				String[] splitElement = tempLine.split(SEP);
				memo.no = Integer.parseInt(splitElement[0]);
				memo.title = splitElement[1];
				memo.content = splitElement[2];
				memo.date = Long.parseLong(splitElement[3]);
				list.add(memo);
			}
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Memo getOne(int index) {
		if(list == null) read();
		
		Memo temp = null;
		for(Memo memo : list) {
			if(memo.no == index) {
				temp = memo;
			}
		}
		return temp;
	}
	
	public void addCount() {
		
		// ������ ���ٸ� ����
		if(!indexFile.exists()) {
			try {
				indexFile.createNewFile();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

		// ������ ���� �����ϹǷ� �ʱ�ȭ �� �� +1
		count++;
	
		// ����� ����
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(indexFile)));
			bw.write(count+"");
			bw.flush();
			bw.close();
		} catch(IOException e) {
			
		}
	}
	
	public void setCount() {
		// �ƹ� ���� �Էµ��� ���� ��Ȳ�� �ƴ϶�� count ���� ������� ������ �ʱ�ȭ �� �ش�
		if(indexFile.exists()) {
			try {
				FileInputStream fis = new FileInputStream(indexFile);
				char index = (char) fis.read();
				System.out.println(index);
				String strIndex = Character.toString(index);
				count = Integer.parseInt(strIndex);
				fis.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
