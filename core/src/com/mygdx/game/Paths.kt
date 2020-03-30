/* Paths: Used to contains paths to resources */
package com.mygdx.game

class Paths {

    companion object {
    }

    class Songs {
        companion object {
            private const val SONGS = "sounds/songs/"
            const val INTRO_THEME = SONGS + "intro.mp3"
        }
    }

    class Clickables {
        companion object {
            private const val CLICKABLES = "clickables/"
            const val PLAY_BUTTON_CLICKED = CLICKABLES + "play_button_clicked.png"
            const val PLAY_BUTTON_NORMAL = CLICKABLES + "play_button_normal.png"
            const val OPTIONS_BUTTON_CLICKED = CLICKABLES + "options_button_clicked.png"
            const val OPTIONS_BUTTON_NORMAL = CLICKABLES + "options_button_normal.png"
            const val ABOUT_BUTTON_CLICKED = CLICKABLES + "about_button_clicked.png"
            const val ABOUT_BUTTON_NORMAL = CLICKABLES + "about_button_normal.png"
        }

        class LevelNodes {
            companion object {
                private const val LEVEL_NODES = CLICKABLES + "level_nodes/"
                const val DEFAULT_NODE_NORMAL = LEVEL_NODES + "default_node_normal.png"
                const val DEFAULT_NODE_CLICKED = LEVEL_NODES + "default_node_clicked.png"
                const val WAREZ_NODE_NORMAL = LEVEL_NODES + "warez_node_normal.png"
                const val WAREZ_NODE_CLICKED = LEVEL_NODES + "warez_node_clicked.png"
            }
        }
    }

    class TextFiles {
        companion object {
            private const val TEXT_FILES = "text_files/"
            const val ABOUT = TEXT_FILES + "about.txt"
            const val OPTIONS = TEXT_FILES + "options.txt"
        }
    }

    class LevelFiles {
        companion object {
            private const val LEVEL_FILES = "level_files/"
            const val LEVEL_LAYOUT = LEVEL_FILES + "level_layout.xml"
        }
    }

    class Skins {
        companion object {
            private const val SKINS = "skins/"
        }
        class Sgx {
            companion object {
                private const val Sgx = SKINS + "sgx/"
                const val JSON = Sgx + "sgx-ui.json"
                const val ATLAS = Sgx + "sgx-ui.atlas"
            }
        }
    }

    class Node {
        companion object {
            const val NODE_PATH = "nodes/"
        }
    }
}