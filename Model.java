import java.util.ArrayList;
import java.util.List;

public class Model {
	
	public static List<Memo> data = new ArrayList<>();
	
	
	// �߰�
	public void add(Memo memo) {
		data.add(memo);
	}
	
	// ��ȸ
	public Memo getOne(int index) {
		Memo temp = null;
		for(Memo memo : data) {
			if(memo.no == index) {
				temp = memo;
			}
		}
		return temp;
	}
	
	// ��ü ��ȸ
	public List<Memo> getAll(){
		return data;
	}
	
	// ����
	public void delete(Memo memo) {
		data.remove(memo);
	}
	

}
