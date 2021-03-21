# JaCoCo Maven Configuration

To use JaCoCo, we need to specify a maven plugin in the 'pom.xml':

'''
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.4</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>prepare-package</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
'''

Now to generate the JaCoCo report we simply need to run mvn test jacoco:report

The class that offered the least coverage was the CuponEuromillions with 40% instructions coverage and 0% branches coverage the reason for this is because 'format()' was not tested.

Most of the decision branches have been covered although there are still some branches that have not been exercised like in the 'equals()' method in 'Dip' class.
