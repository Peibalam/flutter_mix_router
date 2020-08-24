

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class TestCounter extends StatefulWidget {

  @override
  _CounterState createState() {
    return _CounterState();
  }


}

class TestStateStatelessCounter extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Row(
      children: <Widget>[
        CounterIncrementor(onPressed: () {
          var dataModel = Provider.of<CountModel>(context, listen: false);
          dataModel._increment();
//          model._increaseTime();
        }),
        Consumer<CountModel>(
          builder: (context, model, child) {
            return CounterDisplay(count: model._counter);
          },
        ),
        Selector(
          selector: (BuildContext context, CountModel counterProvider) {
            return counterProvider._timer;
          },
          builder: (context, time, child) {
            return TimerDisplay(time: time);
          },
        ),
        Selector(
          selector: (BuildContext context, CountModel model) {
            return model._timer;
          },
          builder: (context, time, child) {
            return TimerDisplay(time: time,);
          },
        )
      ],
    );
  }
}


class CountModel extends ChangeNotifier {
  int _counter = 0;
  int _timer = 0;

  void _increment() {
    _counter++;
    notifyListeners();
  }

  void _increaseTime() {
    _timer++;
    notifyListeners();
  }

  int _getTime() {
    return _timer;
  }

  int _getCount() {
    return _counter;
  }

}

class _CounterState extends State<TestCounter> {

  @override
  Widget build(BuildContext context) {
    var dataModel = Provider.of<CountModel>(context, listen: false);
    return Row(
      children: <Widget>[
        CounterIncrementor(onPressed: () {
          dataModel._increment();
//          model._increaseTime();
        }),
        Consumer<CountModel>(
          builder: (context, model, child) {
            return CounterDisplay(count: model._counter);
          },
        ),
        Selector(
          selector: (BuildContext context, CountModel counterProvider) {
            return counterProvider._timer;
          },
          builder: (context, time, child) {
            return TimerDisplay(time: time);
          },
        ),
        Selector(
          selector: (BuildContext context, CountModel model) {
            return model._timer;
          },
          builder: (context, time, child) {
            return TimerDisplay(time: time,);
          },
        )
      ],
    );
  }
}


class CounterDisplay extends StatelessWidget {
  CounterDisplay({this.count});

  final int count;

  @override
  Widget build(BuildContext context) {
    print('CounterDisplay build');
    return Text('count $count');
  }

}

class TimerDisplay extends StatelessWidget {
  TimerDisplay({this.time});

  final int time;

  @override
  Widget build(BuildContext context) {
    print('TimerDisplay build');
    return Text('time $time');
  }

}

class CounterIncrementor extends StatelessWidget {

  CounterIncrementor({this.onPressed});

  final VoidCallback onPressed;

  @override
  Widget build(BuildContext context) {
    print('CounterIncrementor build');
    return RaisedButton(
      onPressed: onPressed,
      child: Text('Increment'),
    );
  }
}