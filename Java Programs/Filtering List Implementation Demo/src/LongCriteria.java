import java.util.ArrayList;
import java.util.List;

public class LongCriteria implements Criteria {

	@Override
	public List<Hammer> meetCriteria(List<Hammer> hammers) {
		List<Hammer> longHammers = new ArrayList<Hammer>();
		
		for(Hammer ham: hammers){
			if(ham.getHandle()=='l')
				longHammers.add(ham);
		}
		return longHammers;
	}

	
}
