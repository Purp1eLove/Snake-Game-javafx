package com.example.code;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
@Tag("java")
public class ScoreTest {
    static Player player_1;
    static Player player_2;
    static Player player_3;
    static ArrayList<Player> players;
    @BeforeAll
    static void setup(){
        player_1 = new Player(StaticResourcesConfig.Player_Name_1, StaticResourcesConfig.Player_Score_1);
        player_2 = new Player(StaticResourcesConfig.Player_Name_2, StaticResourcesConfig.Player_Score_2);
        player_3 = new Player(StaticResourcesConfig.Player_Name_3, StaticResourcesConfig.Player_Score_3);
        players = new ArrayList<Player>();
        players.add(player_1);
        players.add(player_2);
        players.add(player_3);
        System.out.println("Set up successfully!");
    }
    @AfterAll
    static void cleanup(){
        player_1 = null;
        player_2 = null;
        player_3 = null;
        System.out.println("Clean up successfully!");
    }

    @Test
    @DisplayName("Player name should not be null")
    void Player_Add(){
        Assertions.assertThat(players.get(0).Player_Name).isEqualTo("Yunchi Zhao");
        Assertions.assertThat(players.get(0).Player_Score).isEqualTo(500);
        Assertions.assertThat(players.get(1).Player_Name).isEqualTo("scyyz17");
        Assertions.assertThat(players.get(1).Player_Score).isEqualTo(369);
        Assertions.assertThat(players.get(2).Player_Name).isEqualTo("Simon Zhao");
        Assertions.assertThat(players.get(2).Player_Score).isEqualTo(1080);
    }

    @Test
    @DisplayName("Player should sorted by scores")
    void Player_Sort(){
        PlayerSorter sorter = new PlayerSorter(players);
        players = sorter.GetSortedPlayerByScore();
        Assertions.assertThat(players.get(2).Player_Name).isEqualTo("scyyz17");
        Assertions.assertThat(players.get(2).Player_Score).isEqualTo(369);
        Assertions.assertThat(players.get(1).Player_Name).isEqualTo("Yunchi Zhao");
        Assertions.assertThat(players.get(1).Player_Score).isEqualTo(500);
        Assertions.assertThat(players.get(0).Player_Name).isEqualTo("Simon Zhao");
        Assertions.assertThat(players.get(0).Player_Score).isEqualTo(1080);
    }
}
