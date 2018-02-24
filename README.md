## SER 516 - Lab 2 - Team 09

Team Members:
1. Zain Siddiqui (105, zsiddiq2@asu.edu)
2. Vishakha Singal (106, vsingal1@asu.edu)
3. Varun Srivastava (107, vsriva12@asu.edu)
4. Ganesh Kumar Subramanian Venkatraman (108, gsubra11@asu.edu)
5. Pratik Suryawanshi (109, psuryawa@asu.edu)
6. Sangeetha Swaminathan (110, sswami11@asu.edu)
7. Manish Tandon (111, mtandon3@asu.edu)
8. Maitreyi Thakkar (112, mthakka2@asu.edu) 
9. Janani Thiagarajan (113, jthiaga1@asu.edu)
10. Naga Ravi Teja Thoram (114, nthoram@asu.edu)
11. Adhiraj Tikku (115, atikku1@asu.edu)
12. Nelson Tran (116, nttran9@asu.edu)
13. Mohan Vasantrao Yadav(117, mvasantr@asu.edu)

## Compiling

```
git clone https://github.com/SER516/ProjectTwo_Team09.git && cd ProjectTwo_Team09/src/
javac -Xlint:deprecation -cp .:../lib/jcommon-1.0.23.jar:../lib/jfreechart-1.0.19.jar \
    app/client/*.java \
    app/client/*/*.java \
    app/server/*.java
```

## Running

```
java -cp .:../lib/jcommon-1.0.23.jar:../lib/jfreechart-1.0.19.jar app/client/ClientMain &
java app/server/Server 
```
