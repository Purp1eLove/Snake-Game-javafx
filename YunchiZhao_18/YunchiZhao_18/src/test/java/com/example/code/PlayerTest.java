package com.example.code;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;


@Tag("java")
class PlayerTest {
    static Player player_1;

    @BeforeAll
    static void setup(){
        player_1 = new Player(StaticResourcesConfig.Player_Name_1, StaticResourcesConfig.Player_Score_1);
        System.out.println("Set up successfully!");
    }

    @AfterAll
    static void cleanup(){
        player_1 = null;
        System.out.println("Clean up successfully!");
    }

    @Test
    @DisplayName("Player name should not be null")
    void Player_Name_Not_Null(){
        Assertions.assertThat(player_1.Player_Name)
                .as("Player name should not be null")
                .isNotNull();
    }
    @Test
    @DisplayName("Player score should not be null")
    void Player_Score_Not_Null(){
        Assertions.assertThat(player_1.Player_Score)
                .as("Player score should not be null")
                .isNotNull();
    }
}
