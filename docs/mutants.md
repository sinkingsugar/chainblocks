## Mutations support

Chainblocks supports blocks mutation, so that a program flow and behavior can be altered during runtime.
This allows genetic programming and various optimizations too.

### The `Mutate` block

```clojure
"Hello universe"
(Push) ;; mutate reads values from the stack
;; Const has no input but here we could have a input!
(Mutate (Const "Hello world") [0]) ;; [0], first param
(Assert.Is "Hello universe")
```

In a nutshell the `Mutate` block allows to set block parameters during the program execution.
This is not the case by default for various performance and optimization reasons, the biggest being that a chain containing mutations needs to be validated and optimized again for every actual mutation.
The mutation block tries to amortize this by setting the parameter only if the input has actually changed.

### The `Probe` block

```clojure
10
(Probe (Math.Add 10) [0]) ;; [0], first param
(Assert.Is 20)
(Pop) ;; Probe pushes into the stack the parameter value
(Assert.Is 10)
```

`Probe` is a bit the reverse of `Mutate`, where the latter is a `setParam` the former is `getParam`.
`Probe` pushes the value into the stack in order to allow normal block output flow.
It is especially useful when combined with `Mutate` like:

```clojure
20
(Push) ;; mutate reads values from the stack
10
(Mutate (Probe (Math.Add 10) [0]) [0])
(Assert.Is 30)
(Pop)
(Assert.Is 20)
```

In the above case `Mutate` will proposely ignore and bypass the `Probe` block.

Neural network training idea using a GA

```clojure
(def fitness
  (Chain
    "Fitness"
    [1.0 0.0]
    (Mutate (ANN.Narx 2 [4] 1 5 5))
    (Log)))
(ML.Genetic fitness) ;; TBD, etc
```

In the above case the `ML.Genetic` takes care of wrapping and automatically mutate values