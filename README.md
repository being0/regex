# Learn Java Regex
 
- Samples
    - extractExpParams method: Using group feature, the method extract parameters from an expression like "hi ${firstname} ${lastname}!" by the following regex: "\\$\\{([^}]+)}"
    - isValidPassword method: this method uses [Positive Lookahead](https://www.regular-expressions.info/lookaround.html) technic(using ?=), checks the password to be between 8~50 characters including nonwords or number characters and at least one word character. It uses the following regex: "(?=^.{8,50}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Za-z]).*$"
    
    