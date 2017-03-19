import java.util.List;

public class OrCriteria implements Criteria {

	private Criteria criteria1;
	private Criteria criteria2;
	
	public OrCriteria(Criteria c1, Criteria c2){
		criteria1 = c1;
		criteria2 = c2;
	}
	

	public List<Hammer> meetCriteria(List<Hammer> hammers) {
		List<Hammer> firstCriteriaList = criteria1.meetCriteria(hammers);
		List<Hammer> secondCriteriaList = criteria2.meetCriteria(hammers);
		
		for(Hammer ham : secondCriteriaList){
			if(!firstCriteriaList.contains(ham)){
				firstCriteriaList.add(ham);
			}
		}
		return firstCriteriaList;
	}

}
