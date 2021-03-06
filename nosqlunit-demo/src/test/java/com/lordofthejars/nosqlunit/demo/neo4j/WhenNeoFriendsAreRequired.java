package com.lordofthejars.nosqlunit.demo.neo4j;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static com.lordofthejars.nosqlunit.neo4j.EmbeddedNeo4j.EmbeddedNeo4jRuleBuilder.newEmbeddedNeo4jRule;
import static com.lordofthejars.nosqlunit.neo4j.EmbeddedNeoServerConfigurationBuilder.newEmbeddedNeoServerConfiguration;

import javax.inject.Inject;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;

import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.neo4j.EmbeddedNeo4j;
import com.lordofthejars.nosqlunit.neo4j.Neo4jRule;

public class WhenNeoFriendsAreRequired {

	@ClassRule
	public static EmbeddedNeo4j embeddedNeo4j = newEmbeddedNeo4jRule().build();
	
	@Rule
	public Neo4jRule neo4jRule = new Neo4jRule(newEmbeddedNeoServerConfiguration().build(), this);
	
	@Inject
	private GraphDatabaseService graphDatabaseService;
	
	@Test
	@UsingDataSet(locations="matrix.xml", loadStrategy=LoadStrategyEnum.CLEAN_INSERT)
	public void all_direct_and_inderectly_friends_should_be_counted() {
		MatrixManager matrixManager = new MatrixManager(graphDatabaseService);
		int countNeoFriends = matrixManager.countNeoFriends();
		assertThat(countNeoFriends, is(3));
	}
	
}
