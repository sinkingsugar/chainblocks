# Match

```clojure
(Match
  :Cases [(Seq [(Any)])]
  :Passthrough [(Bool)]
)
```

## Definition


## Parameters
| Name | Type | Default | Description |
|------|------|---------|-------------|
| Cases | `[(Seq [(Any)])]` | `[]` | The cases to match the input against, a nil/None case will match anything. |
| Passthrough | `[(Bool)]` | `true` | The input of this block will be the output. |


## Input
| Type | Description |
|------|-------------|
| `[(Any)]` |  |


## Output
| Type | Description |
|------|-------------|
| `[(Any)]` |  |


## Examples

```clojure
(Match

)
```
