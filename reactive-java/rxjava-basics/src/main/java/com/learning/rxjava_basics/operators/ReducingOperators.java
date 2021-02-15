package com.learning.rxjava_basics.operators;

import io.reactivex.rxjava3.core.Observable;

public class ReducingOperators {
public static void main(String[] args) {
	Observable<Integer> data = Observable.just(1,2,3,4);
	
	//count reduces to Maybe so taht can be subscribed
	//reduces to maybe<long>
	data.count().subscribe(count -> System.out.println("total count "+count));

	//anymathc ,none amtch,allmatch exmaple reduces to maybe of boolean
	
	data.any(number -> number > 10).subscribe(result -> System.out.println("any number greater than 10 "+result));
	
	
	data.any(number -> number > 3).subscribe(result -> System.out.println("any number greater than 3 "+result));
	
	//reduce can be used to caculate suma dn then subscribe
	data.reduce((sum,number) -> sum+number)//Maybe of type retuned by reduce operator -> Maybe<Integer>
			.subscribe(sum -> System.out.println("sum of all numbers "+sum));
}
}
