# mmo4j-protocol

Protocol Buffer messages for enums and structs for cross-language support.

Enum naming convention/design is using abbreviations of the name as a prefix, due to how they are generated for cpp where the values are hoisted, which can cause clashes.

This makes the names not as pretty, but seemed like a decent compromise.
