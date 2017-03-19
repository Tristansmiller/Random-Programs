import java.util.ArrayList;
import java.util.List;

public class FlexCriteria implements Criteria {

	@Override
	public List<Hammer> meetCriteria(List<Hammer> hammers) {
		List<Hammer> flexHammers = new ArrayList<Hammer>();
		
		for(Hammer ham: hammers){
			if(ham.getClaw()==2)
				flexHammers.add(ham);
		}
		return flexHammers;
	}

	
}
