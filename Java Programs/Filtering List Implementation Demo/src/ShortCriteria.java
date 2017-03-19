import java.util.ArrayList;
import java.util.List;

public class ShortCriteria implements Criteria {

	@Override
	public List<Hammer> meetCriteria(List<Hammer> hammers) {
		List<Hammer> shortHammers = new ArrayList<Hammer>();
		
		for(Hammer ham: hammers){
			if(ham.getHandle()=='s')
				shortHammers.add(ham);
		}
		return shortHammers;
	}

	
}
