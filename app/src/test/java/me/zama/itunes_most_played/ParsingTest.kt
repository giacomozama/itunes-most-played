package me.zama.itunes_most_played

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import me.zama.itunes_most_played.data.source.remote.MostPlayedSongsResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class ParsingTest {

    @Test
    fun parse_sample_json() {
        // region raw json data
        val raw =
            """
                {
                  "feed": {
                    "title": "Top Songs",
                    "id": "https://rss.applemarketingtools.com/api/v2/us/music/most-played/20/songs.json",
                    "author": {
                      "name": "Apple",
                      "url": "https://www.apple.com/"
                    },
                    "links": [
                      {
                        "self": "https://rss.applemarketingtools.com/api/v2/us/music/most-played/20/songs.json"
                      }
                    ],
                    "copyright": "Copyright © 2022 Apple Inc. All rights reserved.",
                    "country": "us",
                    "icon": "https://www.apple.com/favicon.ico",
                    "updated": "Wed, 11 May 2022 21:44:11 +0000",
                    "results": [
                      {
                        "artistName": "Future",
                        "id": "1621119447",
                        "name": "WAIT FOR U (feat. Drake & Tems)",
                        "releaseDate": "2022-04-29",
                        "kind": "songs",
                        "artistId": "128050210",
                        "artistUrl": "https://music.apple.com/us/artist/future/128050210",
                        "contentAdvisoryRating": "Explict",
                        "artworkUrl100": "https://is4-ssl.mzstatic.com/image/thumb/Music122/v4/65/f2/06/65f2067b-a8ea-239c-c219-8e0f0282dcea/196589073693.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "18",
                            "name": "Hip-Hop/Rap",
                            "url": "https://itunes.apple.com/us/genre/id18"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/wait-for-u-feat-drake-tems/1621119097?i=1621119447"
                      },
                      {
                        "artistName": "Bad Bunny",
                        "id": "1622045635",
                        "name": "Tití Me Preguntó",
                        "releaseDate": "2022-05-06",
                        "kind": "songs",
                        "artistId": "1126808565",
                        "artistUrl": "https://music.apple.com/us/artist/bad-bunny/1126808565",
                        "artworkUrl100": "https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/3e/04/eb/3e04ebf6-370f-f59d-ec84-2c2643db92f1/196626945068.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "12",
                            "name": "Latin",
                            "url": "https://itunes.apple.com/us/genre/id12"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/tit%C3%AD-me-pregunt%C3%B3/1622045624?i=1622045635"
                      },
                      {
                        "artistName": "Bad Bunny",
                        "id": "1622045625",
                        "name": "Moscow Mule",
                        "releaseDate": "2022-05-06",
                        "kind": "songs",
                        "artistId": "1126808565",
                        "artistUrl": "https://music.apple.com/us/artist/bad-bunny/1126808565",
                        "contentAdvisoryRating": "Explict",
                        "artworkUrl100": "https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/3e/04/eb/3e04ebf6-370f-f59d-ec84-2c2643db92f1/196626945068.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "12",
                            "name": "Latin",
                            "url": "https://itunes.apple.com/us/genre/id12"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/moscow-mule/1622045624?i=1622045625"
                      },
                      {
                        "artistName": "Bad Bunny",
                        "id": "1622045626",
                        "name": "Después de la Playa",
                        "releaseDate": "2022-05-06",
                        "kind": "songs",
                        "artistId": "1126808565",
                        "artistUrl": "https://music.apple.com/us/artist/bad-bunny/1126808565",
                        "artworkUrl100": "https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/3e/04/eb/3e04ebf6-370f-f59d-ec84-2c2643db92f1/196626945068.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "12",
                            "name": "Latin",
                            "url": "https://itunes.apple.com/us/genre/id12"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/despu%C3%A9s-de-la-playa/1622045624?i=1622045626"
                      },
                      {
                        "artistName": "Jack Harlow",
                        "id": "1618136917",
                        "name": "First Class",
                        "releaseDate": "2022-04-08",
                        "kind": "songs",
                        "artistId": "1047679432",
                        "artistUrl": "https://music.apple.com/us/artist/jack-harlow/1047679432",
                        "contentAdvisoryRating": "Explict",
                        "artworkUrl100": "https://is4-ssl.mzstatic.com/image/thumb/Music116/v4/e6/b8/83/e6b88344-8d5d-d353-06bc-3204d87071e6/075679759252.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "18",
                            "name": "Hip-Hop/Rap",
                            "url": "https://itunes.apple.com/us/genre/id18"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/first-class/1618136433?i=1618136917"
                      },
                      {
                        "artistName": "Bad Bunny & Chencho Corleone",
                        "id": "1622045634",
                        "name": "Me Porto Bonito",
                        "releaseDate": "2022-05-06",
                        "kind": "songs",
                        "artistId": "1126808565",
                        "artistUrl": "https://music.apple.com/us/artist/bad-bunny/1126808565",
                        "contentAdvisoryRating": "Explict",
                        "artworkUrl100": "https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/3e/04/eb/3e04ebf6-370f-f59d-ec84-2c2643db92f1/196626945068.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "12",
                            "name": "Latin",
                            "url": "https://itunes.apple.com/us/genre/id12"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/me-porto-bonito/1622045624?i=1622045634"
                      },
                      {
                        "artistName": "Future",
                        "id": "1621119442",
                        "name": "PUFFIN ON ZOOTIEZ",
                        "releaseDate": "2022-04-29",
                        "kind": "songs",
                        "artistId": "128050210",
                        "artistUrl": "https://music.apple.com/us/artist/future/128050210",
                        "contentAdvisoryRating": "Explict",
                        "artworkUrl100": "https://is4-ssl.mzstatic.com/image/thumb/Music122/v4/65/f2/06/65f2067b-a8ea-239c-c219-8e0f0282dcea/196589073693.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "18",
                            "name": "Hip-Hop/Rap",
                            "url": "https://itunes.apple.com/us/genre/id18"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/puffin-on-zootiez/1621119097?i=1621119442"
                      },
                      {
                        "artistName": "Bad Bunny & Rauw Alejandro",
                        "id": "1622045955",
                        "name": "Party",
                        "releaseDate": "2022-05-06",
                        "kind": "songs",
                        "artistId": "1126808565",
                        "artistUrl": "https://music.apple.com/us/artist/bad-bunny/1126808565",
                        "artworkUrl100": "https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/3e/04/eb/3e04ebf6-370f-f59d-ec84-2c2643db92f1/196626945068.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "12",
                            "name": "Latin",
                            "url": "https://itunes.apple.com/us/genre/id12"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/party/1622045624?i=1622045955"
                      },
                      {
                        "artistName": "Bad Bunny",
                        "id": "1622045637",
                        "name": "Un Ratito",
                        "releaseDate": "2022-05-06",
                        "kind": "songs",
                        "artistId": "1126808565",
                        "artistUrl": "https://music.apple.com/us/artist/bad-bunny/1126808565",
                        "artworkUrl100": "https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/3e/04/eb/3e04ebf6-370f-f59d-ec84-2c2643db92f1/196626945068.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "12",
                            "name": "Latin",
                            "url": "https://itunes.apple.com/us/genre/id12"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/un-ratito/1622045624?i=1622045637"
                      },
                      {
                        "artistName": "Bad Bunny & Jhay Cortez",
                        "id": "1622045643",
                        "name": "Tarot",
                        "releaseDate": "2022-05-06",
                        "kind": "songs",
                        "artistId": "1126808565",
                        "artistUrl": "https://music.apple.com/us/artist/bad-bunny/1126808565",
                        "artworkUrl100": "https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/3e/04/eb/3e04ebf6-370f-f59d-ec84-2c2643db92f1/196626945068.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "12",
                            "name": "Latin",
                            "url": "https://itunes.apple.com/us/genre/id12"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/tarot/1622045624?i=1622045643"
                      },
                      {
                        "artistName": "Future",
                        "id": "1621119449",
                        "name": "LOVE YOU BETTER",
                        "releaseDate": "2022-04-29",
                        "kind": "songs",
                        "artistId": "128050210",
                        "artistUrl": "https://music.apple.com/us/artist/future/128050210",
                        "contentAdvisoryRating": "Explict",
                        "artworkUrl100": "https://is4-ssl.mzstatic.com/image/thumb/Music122/v4/65/f2/06/65f2067b-a8ea-239c-c219-8e0f0282dcea/196589073693.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "18",
                            "name": "Hip-Hop/Rap",
                            "url": "https://itunes.apple.com/us/genre/id18"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/love-you-better/1621119097?i=1621119449"
                      },
                      {
                        "artistName": "Bad Bunny",
                        "id": "1622045640",
                        "name": "Yo No Soy Celoso",
                        "releaseDate": "2022-05-06",
                        "kind": "songs",
                        "artistId": "1126808565",
                        "artistUrl": "https://music.apple.com/us/artist/bad-bunny/1126808565",
                        "contentAdvisoryRating": "Explict",
                        "artworkUrl100": "https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/3e/04/eb/3e04ebf6-370f-f59d-ec84-2c2643db92f1/196626945068.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "12",
                            "name": "Latin",
                            "url": "https://itunes.apple.com/us/genre/id12"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/yo-no-soy-celoso/1622045624?i=1622045640"
                      },
                      {
                        "artistName": "Kendrick Lamar",
                        "id": "1623071777",
                        "name": "The Heart Part 5",
                        "releaseDate": "2022-05-08",
                        "kind": "songs",
                        "artistId": "368183298",
                        "artistUrl": "https://music.apple.com/us/artist/kendrick-lamar/368183298",
                        "contentAdvisoryRating": "Explict",
                        "artworkUrl100": "https://is5-ssl.mzstatic.com/image/thumb/Music122/v4/49/5a/65/495a65d4-8809-ff36-9684-04353a67a9ee/22UMGIM50740.rgb.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "18",
                            "name": "Hip-Hop/Rap",
                            "url": "https://itunes.apple.com/us/genre/id18"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/the-heart-part-5/1623071775?i=1623071777"
                      },
                      {
                        "artistName": "Jack Harlow",
                        "id": "1618137276",
                        "name": "Churchill Downs (feat. Drake)",
                        "releaseDate": "2022-05-06",
                        "kind": "songs",
                        "artistId": "1047679432",
                        "artistUrl": "https://music.apple.com/us/artist/jack-harlow/1047679432",
                        "contentAdvisoryRating": "Explict",
                        "artworkUrl100": "https://is4-ssl.mzstatic.com/image/thumb/Music116/v4/e6/b8/83/e6b88344-8d5d-d353-06bc-3204d87071e6/075679759252.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "18",
                            "name": "Hip-Hop/Rap",
                            "url": "https://itunes.apple.com/us/genre/id18"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/churchill-downs-feat-drake/1618136433?i=1618137276"
                      },
                      {
                        "artistName": "Bad Bunny",
                        "id": "1622045948",
                        "name": "Neverita",
                        "releaseDate": "2022-05-06",
                        "kind": "songs",
                        "artistId": "1126808565",
                        "artistUrl": "https://music.apple.com/us/artist/bad-bunny/1126808565",
                        "artworkUrl100": "https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/3e/04/eb/3e04ebf6-370f-f59d-ec84-2c2643db92f1/196626945068.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "12",
                            "name": "Latin",
                            "url": "https://itunes.apple.com/us/genre/id12"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/neverita/1622045624?i=1622045948"
                      },
                      {
                        "artistName": "Bad Bunny & Bomba Estéreo",
                        "id": "1622045962",
                        "name": "Ojitos Lindos",
                        "releaseDate": "2022-05-06",
                        "kind": "songs",
                        "artistId": "1126808565",
                        "artistUrl": "https://music.apple.com/us/artist/bad-bunny/1126808565",
                        "artworkUrl100": "https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/3e/04/eb/3e04ebf6-370f-f59d-ec84-2c2643db92f1/196626945068.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "12",
                            "name": "Latin",
                            "url": "https://itunes.apple.com/us/genre/id12"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/ojitos-lindos/1622045624?i=1622045962"
                      },
                      {
                        "artistName": "Morgan Wallen",
                        "id": "1622093361",
                        "name": "Thought You Should Know",
                        "releaseDate": "2022-05-06",
                        "kind": "songs",
                        "artistId": "829142092",
                        "artistUrl": "https://music.apple.com/us/artist/morgan-wallen/829142092",
                        "contentAdvisoryRating": "Explict",
                        "artworkUrl100": "https://is2-ssl.mzstatic.com/image/thumb/Music112/v4/fc/bb/5b/fcbb5bba-f69e-3694-8d55-10dab3aef16b/22UMGIM47207.rgb.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "6",
                            "name": "Country",
                            "url": "https://itunes.apple.com/us/genre/id6"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/thought-you-should-know/1622093135?i=1622093361"
                      },
                      {
                        "artistName": "Bad Bunny",
                        "id": "1622045954",
                        "name": "Efecto",
                        "releaseDate": "2022-05-06",
                        "kind": "songs",
                        "artistId": "1126808565",
                        "artistUrl": "https://music.apple.com/us/artist/bad-bunny/1126808565",
                        "artworkUrl100": "https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/3e/04/eb/3e04ebf6-370f-f59d-ec84-2c2643db92f1/196626945068.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "12",
                            "name": "Latin",
                            "url": "https://itunes.apple.com/us/genre/id12"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/efecto/1622045624?i=1622045954"
                      },
                      {
                        "artistName": "Bad Bunny & Tony Dize",
                        "id": "1622045951",
                        "name": "La Corriente",
                        "releaseDate": "2022-05-06",
                        "kind": "songs",
                        "artistId": "1126808565",
                        "artistUrl": "https://music.apple.com/us/artist/bad-bunny/1126808565",
                        "contentAdvisoryRating": "Explict",
                        "artworkUrl100": "https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/3e/04/eb/3e04ebf6-370f-f59d-ec84-2c2643db92f1/196626945068.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "12",
                            "name": "Latin",
                            "url": "https://itunes.apple.com/us/genre/id12"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/la-corriente/1622045624?i=1622045951"
                      },
                      {
                        "artistName": "Future",
                        "id": "1621119107",
                        "name": "712PM",
                        "releaseDate": "2022-04-29",
                        "kind": "songs",
                        "artistId": "128050210",
                        "artistUrl": "https://music.apple.com/us/artist/future/128050210",
                        "contentAdvisoryRating": "Explict",
                        "artworkUrl100": "https://is4-ssl.mzstatic.com/image/thumb/Music122/v4/65/f2/06/65f2067b-a8ea-239c-c219-8e0f0282dcea/196589073693.jpg/100x100bb.jpg",
                        "genres": [
                          {
                            "genreId": "18",
                            "name": "Hip-Hop/Rap",
                            "url": "https://itunes.apple.com/us/genre/id18"
                          },
                          {
                            "genreId": "34",
                            "name": "Music",
                            "url": "https://itunes.apple.com/us/genre/id34"
                          }
                        ],
                        "url": "https://music.apple.com/us/album/712pm/1621119097?i=1621119107"
                      }
                    ]
                  }
                }
            """.trimIndent()
        // endregion
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter<MostPlayedSongsResponse>()
        val parsed = jsonAdapter.fromJson(raw)
        assertNotNull(parsed)
        assertEquals(parsed!!.feed.results.size, 20)
    }
}