// ----------------------------------------------------------------------------
// The Wire Class. A wire needs to support three basic actions.
/*
getSignal: Boolean returns the current signal on the wire.
setSignal(sig: Boolean) sets the wire’s signal to sig.
addAction(p: Action) attaches the specified procedure p to the actions of
the wire. All attached action procedures will be executed every time the signal
of a wire changes.
*/
class Wire {
	private var sigVal = false
	private var actions: List[Action] = List()
	
	def getSignal = sigVal
	
	def setSignal(s: Boolean) =
		if (s != sigVal) {
			sigVal = s
			actions.foreach(action => action())
		}
	
	def addAction(a: Action) {
		actions = a :: actions; a()
	}
}

// -----------------------------
// The Inverter Class. 
def inverter(input: Wire, output: Wire) {
	def invertAction() {
		val inputSig = input.getSignal
		afterDelay(InverterDelay) { output setSignal !inputSig }
	}
	
	input addAction invertAction
}

// The And-Gate Class.
def andGate(a1: Wire, a2: Wire, output: Wire) {
	def andAction() {
		val a1Sig = a1.getSignal
		val a2Sig = a2.getSignal
		afterDelay(AndGateDelay) { output setSignal (a1Sig & a2Sig) }
	}
	
	a1 addAction andAction
	a2 addAction andAction
}

// Or-Gate
def orGate(a1: Wire, a2: Wire, output: Wire) {
	def orAction() {
		val a1Sig = a1.getSignal
		val a2Sig = a2.getSignal
		afterDelay(AndGateDelay) { output setSignal (a1Sig | a2Sig) }
	}
	
	a1 addAction orAction
	a2 addAction orAction
}

// ----------------------------------------------------------------------------
// First, there is a class Wire for wires. We can construct wires as follows.
val a = new Wire
val b = new Wire
val c = new Wire

// Second, there are procedures
def inverter(input: Wire, output: Wire)
def andGate(a1: Wire, a2: Wire, output: Wire)
def orGate(o1: Wire, o2: Wire, output: Wire)

// ----------------------------------------------------------------------------
// More complicated function boxes can now be built from these. 
// For instance, to construct a half-adder, we can define:
def halfAdder(a: Wire, b: Wire, s: Wire, c: Wire) {
	val d = new Wire
	val e = new Wire
	
	orGate(a, b, d)
	andGate(a, b, c)
	inverter(c, e)
	andGate(d, e, s)
}

// This abstraction can itself be used, for instance in defining a full adder:
def fullAdder(a: Wire, b: Wire, cin: Wire, sum: Wire, cout: Wire) {
	val s = new Wire
	val c1 = new Wire
	val c2 = new Wire
	
	halfAdder(a, cin, s, c1)
	halfAdder(b, s, sum, c2)
	orGate(c1, c2, cout)
}
// ----------------------------------------------------------------------------
// The Simulation API
// Discrete event simulation performs user-defined actions at specified times.
type Action = () => Unit

// The time is simulated; it is not the actual “wall-clock” time.
// ----------------------------------------------------------------------------
// A concrete simulation will be done inside an object which inherits from the abstract
// Simulation class. This class has the following signature:

abstract class Simulation {
//	def currentTime: Int
//	def afterDelay(delay: Int, action: => Action)
//	def run()
	
	/*
	 The idea is that we maintain inside a Simulation object an agenda of
	actions to perform. The agenda is represented as a list of pairs of actions and the
	times they need to be run. The agenda list is sorted, so that earlier actions come
	before later ones.
	*/
	case class WorkItem(time: Int, action: Action)
	private type Agenda = List[WorkItem]
	private var agenda: Agenda = List()
	
	// There is also a private variable curtime to keep track of the current simulated time.
	private var curtime = 0
	
	// An application of the method afterDelay(delay, block) inserts the element
	// WorkItem(currentTime + delay, () => block) into the agenda list at the appropriate place.
	private def insert(ag: Agenda, item: WorkItem): Agenda =
		if (ag.isEmpty || item.time < ag.head.time) item :: ag
		else ag.head :: insert(ag.tail, item)
	
	def afterDelay(delay: Int)(block: => Unit) {
		val item = WorkItem(curtime + delay, () => block) //currentTime
		agenda = insert(agenda, item)
	}
	
	// An application of the run method removes successive elements from the agenda and
	// performs their actions. It continues until the agenda is empty:
	private def next() {
		agenda match {
			case WorkItem(time, action) :: rest =>
			agenda = rest; curtime = time; action()
			case List() =>
		}
	}
	
	def run() {
		afterDelay(0) { println("***simulation started***") }
		
		while (!agenda.isEmpty) next()
	}
}

// ----------------------------------------------------------------------------
// Running the Simulator. To run the simulator, we still need a way to inspect
// changes of signals on wires. To this purpose, we write a function probe.
	def probe(name: String, wire: Wire) {
		wire addAction { () =>
			println(name + " " + curtime + " new_value = " + wire.getSignal)	//currentTime
		}
	}
// ----------------------------------------------------------------------------
// Now, to see the simulator in action, 
// let’s define four wires, and place probes on two of them:
val input1, input2, sum, carry = new Wire

probe("sum", sum)
probe("carry", carry)

// Now let’s define a half-adder connecting the wires:
halfAdder(input1, input2, sum, carry)

// Finally, set one after another the signals on the two input wires 
// to true and run the simulation.
input1 setSignal true; run
input2 setSignal true; run

