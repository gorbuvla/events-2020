package me.gorbuvla.domain.data

import me.gorbuvla.domain.domain.Address
import me.gorbuvla.domain.domain.Coordinate
import me.gorbuvla.domain.domain.Event
import me.gorbuvla.domain.domain.EventType

// Static data

val conferences = listOf(
    Event(
        id = "google-io",
        title = "Google IO",
        description = "Important",
        image = "https://storage.googleapis.com/io-2018.appspot.com/2020/assets/meta/social-share.jpg",
        type = EventType.CONFERENCE,
        address = Address(
            name = "Shoreline Amphitheatre, Palo Alto",
            location = Coordinate(37.4043677,-122.162594)
        ),
        url = "https://events.google.com/io/"
    ),
    Event(
        id = "android-makers",
        title = "Android Makers",
        description = "Oulala",
        image = "https://androidmakers.fr/images/social-share.jpg",
        type = EventType.CONFERENCE,
        address = Address(
            name = "Beffroi de Montrouge, Paris",
            location = Coordinate(48.818537,2.3175953)
        ),
        url = "https://androidmakers.fr/"
    ),
    Event(
        id = "frenchkit",
        title = "FrenchKit",
        description = "Oulala, but 4x more expensive",
        image = "https://frenchkit.fr/wp-content/uploads/2019/02/frenchkit-d-beffroi.jpg",
        type = EventType.CONFERENCE,
        address = Address(
            name = "Beffroi de Montrouge, Paris",
            location = Coordinate(48.819017,2.3191865)
        ),
        url = "https://frenchkit.fr/"
    ),
    Event(
        id = "mDevCamp",
        title = "mDevCamp",
        description = "My chance to see local superstar David Bilik",
        image = "https://mdevcamp.eu/images/og-mdevcamp-banner.png",
        type = EventType.CONFERENCE,
        address = Address(
            name = "Prague Congress Centre, Prague",
            location = Coordinate(50.062033,14.4263173)
        ),
        url = "https://mdevcamp.eu/"
    ),
    Event(
        id = "droidcon",
        title = "DroidCon Berlin",
        description = "Return of the eastern spy",
        image = "https://static.wixstatic.com/media/6e1ab2_ea2f884b8f8948cf8977aec76cfb0297~mv2.png/v1/fill/w_1958,h_526,al_c,q_90,usm_0.66_1.00_0.01/BER_20_Place_Web_Hero.webp",
        type = EventType.CONFERENCE,
        address = Address(
            name = "City Cube, Berlin",
            location = Coordinate(52.5002212,13.2685643)
        ),
        url = "https://www.berlin.droidcon.com/"
    )
)

val concerts = listOf(
    Event(
        id = "kiwanuka",
        title = "Michael Kiwanuka in Prague",
        description = "Alternative/Indie, Contemporary folk, Soul",
        image = "https://i.ytimg.com/vi/aMZ4QL0orw0/maxresdefault.jpg",
        type = EventType.CONCERT,
        address = Address(
            name = "Forum Karlín, Prague",
            location = Coordinate(50.0914263,14.4526273)
        ),
        url = "https://goout.net/en/concerts/michael-kiwanuka/hgvhf/+vdinp/"
    )
)

val travel = listOf(
    Event(
        id = "barcelona",
        title = "Barcelona",
        description = "Tyvolena",
        image = "https://images.adsttc.com/media/images/5d01/5079/284d/d16c/8700/00bb/slideshow/Barcelona_Architecture-Virginia_Duran-6-Sagrada_Familia.jpg?1560367212",
        type = EventType.TRAVEL,
        address = Address(
            name = "Barcelona",
            location = Coordinate(41.3947688,2.0787279)
        ),
        url = "https://www.barcelona.com/"
    )
)

val movies = listOf(
    Event(
        id = "wonder-woman",
        title = "Wonder Woman 1984",
        description = "80ies soundtrack, just like in Atomic Blonde",
        image = "https://cdn.collider.com/wp-content/uploads/2020/01/wonder-woman-84-image-gal-gadot-slice.jpeg",
        type = EventType.MOVIE,
        address = Address(
            name = "Cinema",
            location = Coordinate(50.0870919,14.4265526)
        ),
        url = "https://www.imdb.com/title/tt7126948/"
    ),
    Event(
        id = "tenet",
        title = "TENET",
        description = "Lets support infamous directors",
        image = "https://m.media-amazon.com/images/M/MV5BNWIwNzZmZDgtNDcxZi00YTFlLWJhYzktOGI5MTM4MzBjYWJkXkEyXkFqcGdeQWpnYW1i._V1_UX477_CR0,0,477,268_AL_.jpg",
        type = EventType.MOVIE,
        address = Address(
            name = "Cinema",
            location = Coordinate(50.0782697,14.4624103)
        ),
        url = "https://www.imdb.com/title/tt6723592/"
    )
)