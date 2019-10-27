(def identity [(Float4 1 0 0 0)
               (Float4 0 1 0 0)
               (Float4 0 0 1 0)
               (Float4 0 0 0 1)])

(def Root (Node))
(schedule Root (Chain "tests"
  (Float3 1 2 3)
  (Math.LinAlg.Cross (Float3 2 2 2))
  (Log)
  (Assert.Is (Float3 -2 4 -2) true)

  (Const [(Float3 1 2 3), (Float3 1 3 4)])
  (Math.LinAlg.Cross (Float3 2 2 2))
  (Log)
  (Assert.Is [(Float3 -2 4 -2) (Float3 -2 6 -4)] true)

  (Const [(Float3 1 2 3), (Float3 1 3 4)])
  (Math.LinAlg.Cross [(Float3 2 2 2) (Float3 3 3 3)])
  (Log)
  (Assert.Is [(Float3 -2 4 -2) (Float3 -3 9 -6)] true)

  (Float3 1 2 3)
  (Math.LinAlg.Dot (Float3 1 5 7))
  (Log)
  (Assert.Is (Float 32) true)

  (Float3 1 2 3)
  (Math.LinAlg.LengthSquared)
  (Log)
  (Assert.Is 14.0 true)

  (Float3 1 1 2)
  (Math.LinAlg.Length)
  (Log)
  (ToString) ; truncate hack
  (Assert.Is "2.44949" true)

  (Float3 1 0 1)
  (Math.LinAlg.Normalize)
  (Log)

  (Float3 4270.56 4602.19 188) (Set "playerPos")
  ; (Float3 4277.35 4596.54 188) (Set "targetPos")
  (Float3 4283.18 4604.73 188) (Set "targetPos")
  (Get "targetPos") (Math.Subtract (# "playerPos"))
  (Math.LinAlg.Normalize)
  (Log)

  (Const identity)
  (Math.LinAlg.MatMul (Float4 1 2 3 4))
  (Log)
))
