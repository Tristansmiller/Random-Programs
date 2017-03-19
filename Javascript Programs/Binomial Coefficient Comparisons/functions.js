function test(){
  if(document.getElementById("bin1n").value==1){
    document.getElementById("bin1time").innerHTML="Success";
  }
  else{
    document.getElementById("bin1time").innerHTML="Fail";
  }
}
function binomial1(){
  document.getElementById("bin1time").innerHTML="Time: "+binomial1Time(document.getElementById("bin1n").value,document.getElementById("bin1k").value,document.getElementById("bin1rep").value)
}

function binomial1Compute(n,k){
  console.log(factorial(n));
  return factorial(n)/(factorial(n-k)*factorial(k));
}
function binomial1Time(n,k,rep){
  var startTime = (new Date).getTime();
  for(var x=0;x<rep;x++){
    document.getElementById("bin1ans").innerHTML="Answer: "+binomial1Compute(document.getElementById("bin1n").value,document.getElementById("bin1k").value)
  }
  var endTime = (new Date).getTime();
  return endTime-startTime;
}

function factorial(num){
    if(num<1){
      return 1;
    }
    return num*factorial(num-1);
}

function binomial2(){
  document.getElementById("bin2time").innerHTML="Time: "+binomial2Time(document.getElementById("bin2n").value,document.getElementById("bin2k").value,document.getElementById("bin1rep").value)
}

function binomial2Compute(n,k){
  var answer = 1;
  for(var i=1;i<=k;i++){
    answer*=(n-i+1)/i;
  }
  return answer;
}
function binomial2Time(n,k,rep){
  var startTime = (new Date).getTime();
  for(var x =0;x<rep;x++){
    document.getElementById("bin2ans").innerHTML="Answer: "+binomial2Compute(document.getElementById("bin2n").value,document.getElementById("bin2k").value)
  }
  var endTime = (new Date).getTime();
  return endTime-startTime;
}

function binomial3(){
  document.getElementById("bin3time").innerHTML="Time: "+binomial3Time(document.getElementById("bin3n").value,document.getElementById("bin3k").value,document.getElementById("bin1rep").value)
}

function binomial3Time(n,k,rep){
  var startTime = (new Date).getTime();
  for(var x =0;x<rep;x++){
    document.getElementById("bin3ans").innerHTML="Answer: "+binomial3Compute(document.getElementById("bin3n").value,document.getElementById("bin3k").value)
  }
  var endTime = (new Date).getTime();
  return endTime-startTime;
}
function binomial3Compute(n,k){
  if(k==1) return n;
  else return (n/k)*binomial3Compute(n-1,k-1);
}
