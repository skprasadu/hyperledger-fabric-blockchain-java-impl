rm -rf fabric-samples

git clone https://github.com/hyperledger/fabric-samples.git

pushd fabric-samples

# Fetch bootstrap.sh from fabric repository using
curl -sS https://raw.githubusercontent.com/hyperledger/fabric/master/scripts/bootstrap.sh -o ./scripts/bootstrap.sh
# Change file mode to executable
chmod +x ./scripts/bootstrap.sh
# Download binaries and docker images
./scripts/bootstrap.sh 

popd

mkdir -p fabric-samples/chaincode/java-chaincode/java
mkdir -p fabric-samples/java-chaincode

pushd java-chaincode

cp -a * ../fabric-samples/chaincode/java-chaincode/java/

popd

cp startFabric.sh fabric-samples/java-chaincode/.

cp -R java-server fabric-samples/java-chaincode/.

pushd fabric-samples/java-chaincode

PATH=../bin:$PATH sh ./startFabric.sh java

popd

pushd fabric-samples/java-chaincode/java-server

mvn test
