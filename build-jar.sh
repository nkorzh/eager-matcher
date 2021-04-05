BUILD_PATH="$PWD"/__build
rm -rf $BUILD_PATH
mkdir "$BUILD_PATH"
SRC_PATH=$PWD/src
javac -d "$BUILD_PATH" $SRC_PATH/*.java

cd $BUILD_PATH

rm -f ./../TestMatcher.jar
jar cfe ./../TestMatcher.jar TestMatcher *.class

cd ..
rm -rf $BUILD_PATH
