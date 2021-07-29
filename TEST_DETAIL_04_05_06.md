## LazyConnectionDataSourceProxy Test

[1] 기능 구현
  1. LazyConnectionDataSourceProxy 을 사용해 Master,Slave 구분
  2. Multi DataSource 설정


[2] 테스트 한 내용    

  1. LazyConnectionDataSourceProxy ( DatabaseConfig.java 설정 )
  

     1-(1) outer(readWrite master) => REQUIRED && inner(readOnly slave) => NEW_REQUIRED 인 경우, 
           inner 에서 readOnly 전용 dataSoruce 가 생기지 않고 outer 의 dataSource 에 의해 데이터를 읽어옴
  
     1-(2) outer(readOnly slave) => REQUIRED && inner(readWrite master) => NEW_REQUIRED 인 경우,
           inner 에서 새로운 dataSource 가 부여되지 않고 outer 의 dataSource 를 사용한다. 때문에, write 에 실패하면서 에러 발생.
     
     1-(3) 참고 : https://taes-k.github.io/2020/03/11/sprinig-master-slave-dynamic-routing-datasource/

  2. Multi DataSource 설정


     2-(1) Transactional Propagtion.REQUIRES_NEW 와 똑같이 작동한다 ( https://jobc.tistory.com/214 ) 

  3. ChainedTransactionManager 설정


     3-(1)  ChainedTransactionManager 는 Datasource 상에서의 에러 발생 시에는 rollback 이 보장된다.
            다만, 후순위의 logic commit 시 에러가 났을 경우에만 문제가 발생 할 수 있다.
            현재는 Depreciated 되었기 때문에 추후 사용 목적보다는 이런게 있다는 것 정도를 알고 넘어가기 위한 테스트
            ( JTA 를 사용하는 것을 권장 -> 2 phase commit )


 [3] 참고
 
   1. Read Replica 가 여러개인 경우 Route53 등을 이용해 infra 측면에서의 DB load balancing 이 필요함.
   2. Transactional Propagtion.REQUIRES_NEW 에 대한 오해와 진실 : https://jobc.tistory.com/214